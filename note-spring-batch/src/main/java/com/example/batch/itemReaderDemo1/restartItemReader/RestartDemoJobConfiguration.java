//package com.example.batch.itemReaderDemo1.restartItemReader;
//
//import com.example.business.entity.UserInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class RestartDemoJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    @Qualifier("flatFileDemoWriter")
//    private ItemWriter<? super UserInfo> flatFileDemoWriter;
//
//    @Autowired
//    @Qualifier("restartDemoReader")
//    private ItemReader<UserInfo> restartDemoReader;
//
//
//    @Bean
//    public Job restartDemoJob(){
//        return jobBuilderFactory.get("restartDemoJob")
//                .start(restartDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step restartDemoStep(){
//        return stepBuilderFactory.get("restartDemoStep")
//                .<UserInfo, UserInfo>chunk(100)
//                .reader(restartDemoReader)
//                .writer(flatFileDemoWriter)
//                .build();
//    }
//}
