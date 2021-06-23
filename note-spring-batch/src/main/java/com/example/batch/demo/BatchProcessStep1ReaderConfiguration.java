package com.example.batch.demo;

import com.example.business.entity.UserInfo;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchProcessStep1ReaderConfiguration {

    @Bean("batchProcessStep1Reader")
    @StepScope
    public ItemReader<UserInfo> batchProcessStep1Reader(){
        return null;
    }
}
