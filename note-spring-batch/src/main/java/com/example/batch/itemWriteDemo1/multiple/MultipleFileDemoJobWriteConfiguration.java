//package com.example.batch.itemWriteDemo1.multiple;
//
//import com.example.batch.itemWriteDemo1.flatFile.UserInfoLineAggregator;
//import com.example.business.entity.UserInfo;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.support.CompositeItemWriter;
//import org.springframework.batch.item.xml.StaxEventItemWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.oxm.xstream.XStreamMarshaller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class MultipleFileDemoJobWriteConfiguration {
//
//    @Bean
//    public StaxEventItemWriter<UserInfo> xmlFileWrite() throws Exception {
//        XStreamMarshaller marshaller = new XStreamMarshaller();
//        Map<String,Class> aliases = new HashMap<>();
//        aliases.put("userInfo",UserInfo.class);
//        marshaller.setAliases(aliases);
//
//        StaxEventItemWriter<UserInfo> itemWriter = new StaxEventItemWriter<>();
//        itemWriter.setRootTagName("userInfos");
//        itemWriter.setMarshaller(marshaller);
//        String path = File.createTempFile("userInfo", ".xml").getAbsolutePath();
//        System.out.println("xml file is create in: " + path);
//        itemWriter.setResource(new FileSystemResource(path));
//        itemWriter.afterPropertiesSet();
//
//        return itemWriter;
//    }
//
//    @Bean
//    public FlatFileItemWriter<UserInfo> jsonFileWrite() throws Exception {
//        FlatFileItemWriter<UserInfo> itemWriter = new FlatFileItemWriter<>();
//        String path = File.createTempFile("userInfo",".json").getAbsolutePath();
//        System.out.println("json file is create in: " + path);
//        itemWriter.setResource(new FileSystemResource(path));
//        itemWriter.setLineAggregator(new UserInfoLineAggregator());
//        itemWriter.afterPropertiesSet();
//        return itemWriter;
//    }
//
//    @Bean
//    public CompositeItemWriter<UserInfo> UserInfoCompositeItemWrite() throws Exception {
//        CompositeItemWriter<UserInfo> itemWriter = new CompositeItemWriter<>();
//        itemWriter.setDelegates(Arrays.asList(xmlFileWrite(), jsonFileWrite()));
//        itemWriter.afterPropertiesSet();
//        return itemWriter;
//
//    }
//}
