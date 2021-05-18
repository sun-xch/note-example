package com.example.batch.demo.jobRetry;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class JobRetryConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("retryItemWriter")
    private ItemWriter<String> RetryItemWriter;

    @Bean
    public Job jobRetryTest() {
        return jobBuilderFactory.get("jobRetryTest")
                .start(jobRetryStep())
                .build();
    }

    /**
     * 重试N次是指每个item重试N次
     * @return
     */
    @Bean
    public Step jobRetryStep() {
        return stepBuilderFactory.get("jobRetryStep")
                .<String, String>chunk(10)
                .reader(reader())
                .writer(RetryItemWriter)
                .faultTolerant()
                .retry(RuntimeException.class)
                .retryLimit(10)
                .build();
    }

    @Bean
    @StepScope
    public ListItemReader reader() {

        List<String> items = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            items.add(String.valueOf(i));
        }

        ListItemReader<String> reader = new ListItemReader(items);

        return reader;
    }
}
