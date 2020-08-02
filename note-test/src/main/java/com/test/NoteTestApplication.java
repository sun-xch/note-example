package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.test.AudioMedia.dao")//使用MapperScan批量扫描所有的Mapper接口；
public class NoteTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteTestApplication.class, args);
    }

}
