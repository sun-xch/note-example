package com.test.ffmpegtest;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestFFmpegWrapper {

    @Test
    public void testVideo() throws IOException {

        FFmpeg ffmpeg = new FFmpeg();
        FFprobe ffprobe = new FFprobe();

        final FFmpegProbeResult probe = ffprobe.probe("D:/videoTest/hls_1/test1.mp4");

        final List<FFmpegStream> streams = probe.getStreams().stream().filter(fFmpegStream -> fFmpegStream.codec_type != null).collect(Collectors.toList());

        final Optional<FFmpegStream> audioStream = streams.stream().filter(fFmpegStream -> FFmpegStream.CodecType.AUDIO.equals(fFmpegStream.codec_type)).findFirst();

        final Optional<FFmpegStream> videoStream = streams.stream().filter(fFmpegStream -> FFmpegStream.CodecType.VIDEO.equals(fFmpegStream.codec_type)).findFirst();

        if (!audioStream.isPresent()) {
            System.err.println("未发现音频流");
        }

        if (!videoStream.isPresent()) {
            System.err.println("未发现视频流");
        }


        FFmpegBuilder builder = new FFmpegBuilder()

                .setInput("D:/videoTest/hls_1/test1.mp4")     // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists

                .addOutput("D:/videoTest/hls_1/output.mp4")   // Filename for the destination
                .setFormat("mp4")        // Format is inferred from filename, or can be set
                //.setTargetSize(250_000)  // Aim for a 250KB file
                .setAudioBitRate(audioStream.isPresent() ? audioStream.get().bit_rate : 0)

                .setVideoBitRate(videoStream.isPresent() ? videoStream.get().bit_rate : 0)

                .disableSubtitle()       // No subtiles

                .setAudioChannels(1)         // Mono audio
                .setAudioCodec("aac")        // using the aac codec
                .setAudioSampleRate(48_000)  // at 48KHz
                .setAudioBitRate(32768)      // at 32 kbit/s

                .setVideoCodec("libx264")     // Video using x264
                .setVideoFrameRate(24, 1)     // at 24 frames per second
                .setVideoResolution(640, 480) // at 640x480 resolution

                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        // Run a one-pass encode
        executor.createJob(builder).run();

        // Or run a two-pass encode (which is better quality at the cost of being slower)
        executor.createTwoPassJob(builder).run();
    }

    @Test
    public void testAudio() throws IOException {
        FFmpeg ffmpeg = new FFmpeg();
        FFprobe ffprobe = new FFprobe();

        final FFmpegProbeResult probe = ffprobe.probe("D:/videoTest/hls_1/test1.mp4");

        final List<FFmpegStream> streams = probe.getStreams().stream().filter(fFmpegStream -> fFmpegStream.codec_type != null).collect(Collectors.toList());

        final Optional<FFmpegStream> audioStream = streams.stream().filter(fFmpegStream -> FFmpegStream.CodecType.AUDIO.equals(fFmpegStream.codec_type)).findFirst();

        if (!audioStream.isPresent()) {
            System.err.println("未发现音频流");
        }

        FFmpegBuilder builder = new FFmpegBuilder()
                /**
                 * 设置源文件
                 */
                .setInput("D:/videoTest/hls_1/test1.mp4")
                /**
                 * 是否覆盖
                 */
                .overrideOutputFiles(true)
                /**
                 * 输出文件位置
                 */
                .addOutput("D:/videoTest/hls_1/output-wav.m3u8")
                /**
                 * 验证格式不为空
                 */
                .setFormat("wav")
                /**
                 * 设置音频比特率
                 */
                .setAudioBitRate(audioStream.isPresent() ? audioStream.get().bit_rate : 0)
                /**
                 * 设置音频频道数
                 */
                .setAudioChannels(1)
                /**
                 * 使用aac编解码器
                 */
                .setAudioCodec("aac")
                /**
                 * 设置音频采样率
                 */
                .setAudioSampleRate(audioStream.get().sample_rate)
                .setAudioBitRate(audioStream.get().bit_rate)
                .setStrict(FFmpegBuilder.Strict.STRICT)
                .setFormat("hls")
                /**
                 * -hls_wrap n ：设置多少片之后开始覆盖，设置为0则不会覆盖，默认值为0。这个选项能够避免在磁盘上存储过多的片，而且能够限制写入磁盘的最多的片的数量。
                 * -hls_time n ：设置每片的长度，默认值为2，单位为秒。
                 * -hls_list_size n ：设置m3u8文件播放列表保存的最多条目，设置为0会保存有所片信息，默认值为5。一般用于直播流，点播文件可以设置成0，即全部保存。
                 */
                .addExtraArgs("-hls_wrap", "0", "-hls_time", "5", "-hls_list_size", "0")
                .done();


        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        // Run a one-pass encode
        executor.createJob(builder).run();
    }

}
