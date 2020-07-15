package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.business.sys.dao")//使用MapperScan批量扫描所有的Mapper接口；
public class NoteSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteSpringSecurityApplication.class, args);
    }

}
