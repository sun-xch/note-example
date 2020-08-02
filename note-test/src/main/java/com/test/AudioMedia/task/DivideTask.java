package com.test.AudioMedia.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.AudioMedia.entity.AudioMediaFile;
import com.test.AudioMedia.entity.MediaFile;
import com.test.AudioMedia.entity.PlayList;
import com.test.AudioMedia.entity.TransportSegment;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFmpegUtils;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import net.bramp.ffmpeg.progress.Progress;
import net.bramp.ffmpeg.progress.ProgressListener;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class DivideTask implements Callable<ImmutablePair<PlayList, List<TransportSegment>>> {

    final Locale locale = Locale.US;

    final FFmpeg ffmpeg = new FFmpeg();

    final FFprobe ffprobe = new FFprobe();

    ImmutablePair<FFmpegProbeResult, AudioMediaFile> pair;

    String filename;
    String uuid;
    byte[] data;

    public DivideTask(ImmutablePair<FFmpegProbeResult,AudioMediaFile> pair) throws IOException {
        this.pair = pair;
    }

    public DivideTask(String filename,String uuid,byte[] data) throws IOException {
        this.filename = filename;
        this.uuid = uuid;
        this.data = data;

        //获取反序列化后文件的元数据信息
        FFmpegProbeResult probeResult = ffprobe.probe(filename);

        long timestamp = LocalDateTime.now(ZoneId.of("UTC+8")).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();

        String metadata = toJson(probeResult);

        AudioMediaFile.Builder builder = new AudioMediaFile.Builder()
                .name(filename)
                .uuid(uuid)
                .streams(probeResult.streams)
                .mimeType(probeResult.format.format_long_name)
                .type(MediaFile.TYPE_AUDIO)
                .stamp(timestamp)
                .bitRate(Long.valueOf(probeResult.format.bit_rate).intValue())
                .duration(Double.valueOf(probeResult.format.duration).floatValue())
                .formatName(probeResult.format.format_name)
                .nbStreams((byte) probeResult.format.nb_streams)
                .size(probeResult.format.size)
                .probeScore((byte) probeResult.format.probe_score)
                .mimeType(probeResult.format.format_long_name)
                .data(data)
                .metadata(metadata);
        this.pair = new ImmutablePair<>(probeResult,builder.build());
    }

    public static String getString(InputStream stream) throws IOException {
        return IOUtils.toString(stream,"UTF-8");
    }

    @Override
    public ImmutablePair<PlayList,List<TransportSegment>> call() throws Exception {

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        final FFmpegProbeResult probe = pair.getLeft();
        AudioMediaFile audioFile = pair.getRight();

        final List<FFmpegStream> streams = probe.getStreams().stream().filter(fFmpegStream -> fFmpegStream.codec_type!=null).collect(Collectors.toList());

        final Optional<FFmpegStream> audioStream = streams.stream().filter(fFmpegStream -> FFmpegStream.CodecType.AUDIO.equals(fFmpegStream.codec_type)).findFirst();

        if(!audioStream.isPresent())
        {
            log.error("未发现音频流");
        }


        String  filename = probe.format.filename;

        Path nioFile = Paths.get(filename);

        String directory = nioFile.getParent().toString();

        String uuid = audioFile.getUuid();

        String output = String.format("%s%sstream.m3u8",directory, File.separator);


        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(filename)
                .overrideOutputFiles(true)
                .addOutput(output)
                .setFormat("wav")
                .setAudioBitRate(audioStream.isPresent()?audioStream.get().bit_rate:0)
                .setAudioChannels(1)
                .setAudioCodec("aac")        // using the aac codec
                .setAudioSampleRate(audioStream.get().sample_rate)
                .setAudioBitRate(audioStream.get().bit_rate)
                .setStrict(FFmpegBuilder.Strict.STRICT)
                .setFormat("hls")
                .addExtraArgs("-hls_wrap", "0", "-hls_time", "5", "-hls_list_size","0")
                .done();


        FFmpegJob job =
                executor.createJob(
                        builder,
                        new ProgressListener() {

                            // Using the FFmpegProbeResult determine the duration of the input
                            final double duration_ns = probe.getFormat().duration * TimeUnit.SECONDS.toNanos(1);

                            @Override
                            public void progress(Progress progress) {
                                double percentage = progress.out_time_ns / duration_ns;

                                // Print out interesting information about the progress
                                String consoleLog = String.format(
                                        locale,
                                        "[%.0f%%] status:%s frame:%d time:%s fps:%.0f speed:%.2fx",
                                        percentage * 100,
                                        progress.status,
                                        progress.frame,
                                        FFmpegUtils.toTimecode(progress.out_time_ns, TimeUnit.NANOSECONDS),
                                        progress.fps.doubleValue(),
                                        progress.speed);
                                log.debug(consoleLog);
                            }
                        });

        job.run();

        if (job.getState() == FFmpegJob.State.FINISHED) {

            //排除的文件
            String[] excludes = new String[]{
                    "wav","m3u8","mp4"
            };

            List<TransportSegment> segments = Files.list(Paths.get(directory)).filter(
                    path -> {
                        String extension = getFileExtension(path.getFileName().toString());
                        return !Arrays.asList(excludes).contains(extension);
                    }
            ).map(path -> {
                String name = path.getFileName().toString();
                try {
                    byte[] bytes = IOUtils.toByteArray(path.toUri());
                    TransportSegment segment = new TransportSegment
                            .Builder()
                            .bytes(bytes)
                            .filename(name)
                            .uuid(UUID.randomUUID().toString().replaceAll("-",""))
                            .build();
                    return segment;
                } catch (IOException e) {
                    log.error("读取文件失败:{}",e);
                }
                return null;
            }).collect(Collectors.toList());

            String context = getString(new FileInputStream(output));
            PlayList playList = new PlayList.Builder()
                    .context(context)
                    .uuid(uuid)
                    .duration(Double.valueOf(probe.format.duration).floatValue())
                    .build();

            return  new ImmutablePair<>(playList,segments);

        }else {
            log.error("文件分割发生不可预料的错误:{}");
        }

        return null;
    }

    private static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    static String toJson(Object value) throws JsonProcessingException {
        ObjectMapper objectMapper =new ObjectMapper();
        //属性值为null不输出
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //默认值的不输出
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        //反斜杠转义其他字符
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,true);
        //所有键值用字符串形式包装起来
        objectMapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS,true);
        return objectMapper.writeValueAsString(value);
    }
}
