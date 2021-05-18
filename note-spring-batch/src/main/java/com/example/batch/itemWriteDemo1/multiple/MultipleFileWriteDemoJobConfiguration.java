//package com.example.batch.itemWriteDemo1.multiple;
//
//import com.example.business.entity.UserInfo;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MultipleFileWriteDemoJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    @Qualifier("multipleFileDemoJdbcPagingReader")
//    private ItemReader<UserInfo> multipleFileDemoJdbcPagingReader;
//
//    @Autowired
//    @Qualifier("UserInfoCompositeItemWrite")
//    private ItemWriter<UserInfo> UserInfoCompositeItemWrite;
//
//    @Bean
//    public Job multipFileWriteJobDemo(){
//        return jobBuilderFactory.get("multipFileWriteJobDemo")
//                .start(multipFileWriteStepDemo())
//                .build();
//    }
//
//    private Step multipFileWriteStepDemo() {
//        return stepBuilderFactory.get("multipFileWriteStepDemo")
//                .<UserInfo, UserInfo>chunk(100)
//                .reader(multipleFileDemoJdbcPagingReader)
//                .writer(UserInfoCompositeItemWrite)
//                .build();
//    }
//}
