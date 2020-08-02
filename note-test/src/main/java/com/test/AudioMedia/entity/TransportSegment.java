package com.test.AudioMedia.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

/***
 * 转换后的文件切片
 */
@Data
@NoArgsConstructor
public class TransportSegment {

    String uuid;

    /***
     * 文件名
     */
    private String filename;

    /***
     * 字节流
     */
    private byte[] bytes;

    private LocalDateTime createTime = LocalDateTime.now(ZoneId.of("+8"));

    private TransportSegment(Builder builder) {
        setUuid(builder.uuid);
        setFilename(builder.filename);
        setBytes(builder.bytes);
        setCreateTime(builder.createTime);
    }


    public static final class Builder {
        private String uuid;
        private String filename;
        private byte[] bytes;
        private LocalDateTime createTime = LocalDateTime.now(ZoneId.of("+8"));

        public Builder() {
        }

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder bytes(byte[] bytes) {
            this.bytes = bytes;
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public TransportSegment build() {
            return new TransportSegment(this);
        }
    }
}
