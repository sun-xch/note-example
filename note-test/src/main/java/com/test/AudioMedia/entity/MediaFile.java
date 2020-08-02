package com.test.AudioMedia.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 多媒体文件
 */
@Data
@NoArgsConstructor
public class MediaFile {

    /**
     * 音频文件
     */
    public static final byte TYPE_AUDIO = 0x1;

    public static final byte TYPE_VIDEO = 0x2;

    public static final byte TYPE_DATA1 = 0x4;

    public static final byte TYPE_DATA2 = 0x8;

    /**
     * 文件唯一标识
     */
    private String uuid;

    /**
     * 文件名
     */
    private String name;

    /**
     * 解析后的数据流
     */
    private byte[] data;

    /**
     * 多媒体文件类型
     */
    private String mimeType;

    /**
     * 创建文件的时间
     */
    private Long stamp;

    /**
     * 文件大小
     */
    private Long size;

    private MediaFile(Builder builder) {
        setUuid(builder.uuid);
        setName(builder.name);
        setData(builder.data);
        setMimeType(builder.mimeType);
        setStamp(builder.stamp);
        setSize(builder.size);
    }

    public static final class Builder {
        private String uuid;
        private String name;
        private byte[] data;
        private String mimeType;
        private Long stamp;
        private Long size;

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

        public MediaFile build() {
            return new MediaFile(this);
        }
    }
}
