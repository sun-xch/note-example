//package com.example.batch.itemWriteDemo1.flatFile;
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
//public class FlatFileWriteDemoJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    @Qualifier("flatFileDemoJdbcPagingReader")
//    private ItemReader<UserInfo> flatFileDemoJdbcPagingReader;
//
//    @Autowired
//    @Qualifier("flatFileDemoFlatFileWrite")
//    private ItemWriter<UserInfo> flatFileDemoFlatFileWrite;
//
//    @Bean
//    public Job flatFileWriteDemoJob(){
//        return jobBuilderFactory.get("flatFileWriteDemoJob")
//                .start(flatFileWriteDemoStep())
//                .build();
//    }
//
//    private Step flatFileWriteDemoStep() {
//        return stepBuilderFactory.get("flatFileWriteDemoStep")
//                .<UserInfo, UserInfo>chunk(100)
//                .reader(flatFileDemoJdbcPagingReader)
//                .writer(flatFileDemoFlatFileWrite)
//                .build();
//    }
//
//
//}
