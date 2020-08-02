package com.test.AudioMedia.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;

@NoArgsConstructor
@Data
public class PlayList {

    private String uuid;

    /***
     * 播放时长
     */
    private Float duration;

    /***
     * 播放列表内容
     */
    private String context;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime = LocalDateTime.now(ZoneId.of("+8"));

    private PlayList(Builder builder) {
        setUuid(builder.uuid);
        setDuration(builder.duration);
        setContext(builder.context);
        setCreateTime(builder.createTime);
    }


    public static final class Builder {
        private String uuid;
        private Float duration;
        private String context;
        private LocalDateTime createTime = LocalDateTime.now(ZoneId.of("+8"));

        public Builder() {
        }

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder duration(Float duration) {
            this.duration = duration;
            return this;
        }

        public Builder context(String context) {
            this.context = context;
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public PlayList build() {
            return new PlayList(this);
        }
    }
}
