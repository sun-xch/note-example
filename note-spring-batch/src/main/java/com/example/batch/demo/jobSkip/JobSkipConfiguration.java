package com.example.batch.demo.jobSkip;

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
public class JobSkipConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("skipItemWriter")
    private ItemWriter<String> skipItemWriter;

    @Bean
    public Job jobSkipTest() {
        return jobBuilderFactory.get("jobSkipTest")
                .start(jobSkipStep())
                .build();
    }

    @Bean
    public Step jobSkipStep() {
        return stepBuilderFactory.get("jobSkipStep")
                .<String, String>chunk(10)
                .reader(reader())
                .writer(skipItemWriter)
                .faultTolerant()
                .skip(RuntimeException.class)
                .skipLimit(5)
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
