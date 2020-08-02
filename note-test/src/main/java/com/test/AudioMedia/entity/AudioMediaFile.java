package com.test.AudioMedia.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bramp.ffmpeg.probe.FFmpegStream;

import java.util.List;

@Data
@NoArgsConstructor
public class AudioMediaFile extends MediaFile {

    /**
     * 流通道数
     */
    private byte nbStreams;

    private byte nbPrograms;

    private Integer startTime;

    /**
     * 格式名称
     */
    private String formatName;

    /**
     * 多媒体播放时长
     */
    private Float duration;

    /**
     * 比特率
     */
    private Integer bitRate;

    private byte probeScore;

    /**
     * 文件类型
     */
    private byte type;

    private List<FFmpegStream> streams;

    private String metadata;


    private AudioMediaFile(Builder builder) {
        setUuid(builder.uuid);
        setName(builder.name);
        setData(builder.data);
        setMimeType(builder.mimeType);
        setStamp(builder.stamp);
        setSize(builder.size);
        setNbStreams(builder.nbStreams);
        setFormatName(builder.formatName);
        setDuration(builder.duration);
        setBitRate(builder.bitRate);
        setProbeScore(builder.probeScore);
        setType(builder.type);
        setStreams(builder.streams);
        setMetadata(builder.metadata);
    }

    public static final class Builder {
        private String uuid;
        private String name;
        private byte[] data;
        private String mimeType;
        private Long stamp;
        private Long size;
        private byte nbStreams;
        private String formatName;
        private Float duration;
        private Integer bitRate;
        private byte probeScore;
        private byte type;
        private List<FFmpegStream> streams;
        private String metadata;

        public Builder() {
        }

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder data(byte[] data) {
            this.data = data;
            return this;
        }

        public Builder mimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Builder stamp(Long stamp) {
            this.stamp = stamp;
            return this;
        }

        public Builder size(Long size) {
            this.size = size;
            return this;
        }

        public Builder nbStreams(byte nbStreams) {
            this.nbStreams = nbStreams;
            return this;
        }

        public Builder formatName(String formatName) {
            this.formatName = formatName;
            return this;
        }

        public Builder duration(Float duration) {
            this.duration = duration;
            return this;
        }

        public Builder bitRate(Integer bitRate) {
            this.bitRate = bitRate;
            return this;
        }

        public Builder probeScore(byte probeScore) {
            this.probeScore = probeScore;
            return this;
        }

        public Builder type(byte type) {
            this.type = type;
            return this;
        }

        public Builder streams(List<FFmpegStream> streams) {
            this.streams = streams;
            return this;
        }

        public Builder metadata(String metadata) {
            this.metadata = metadata;
            return this;
        }

        public AudioMediaFile build() {
            return new AudioMediaFile(this);
        }
    }

}
