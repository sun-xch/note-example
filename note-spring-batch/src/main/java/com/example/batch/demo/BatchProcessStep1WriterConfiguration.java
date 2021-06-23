package com.example.batch.demo;

import com.example.business.entity.UserInfo;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchProcessStep1WriterConfiguration {

    @Bean("batchProcessStep1Writer")
    @StepScope
    public ItemWriter<UserInfo> batchProcessStep1Writer(){
        return null;
    }
}
