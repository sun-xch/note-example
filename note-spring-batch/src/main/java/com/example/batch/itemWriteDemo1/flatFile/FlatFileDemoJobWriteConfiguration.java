//package com.example.batch.itemWriteDemo1.flatFile;
//
//import com.example.business.entity.UserInfo;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//
//import java.io.File;
//
//@Configuration
//public class FlatFileDemoJobWriteConfiguration {
//
//    @Bean
//    public FlatFileItemWriter<UserInfo> flatFileDemoFlatFileWrite() throws Exception {
//        FlatFileItemWriter<UserInfo> itemWriter = new FlatFileItemWriter<>();
//        String path = File.createTempFile("userInfo",".data").getAbsolutePath();
//        System.out.println("file is create in: " + path);
//        itemWriter.setResource(new FileSystemResource(path));
//        itemWriter.setLineAggregator(new UserInfoLineAggregator());
//        itemWriter.afterPropertiesSet();
//        return itemWriter;
//    }
//}
