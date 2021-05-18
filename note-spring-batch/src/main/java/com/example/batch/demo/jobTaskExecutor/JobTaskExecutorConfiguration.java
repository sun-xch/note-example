package com.example.batch.demo.jobTaskExecutor;

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
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class JobTaskExecutorConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("taskExecutorItemWriter")
    private ItemWriter<String> taskExecutorItemWriter;

    @Bean
    public Job jobTaskExecutorTest() {
        return jobBuilderFactory.get("jobTaskExecutorTest")
                .start(jobTaskExecutorStep())
                .build();
    }

    @Bean
    public Step jobTaskExecutorStep() {
        return stepBuilderFactory.get("jobTaskExecutorStep")
                .<String, String>chunk(10)
                .reader(reader())
                .writer(taskExecutorItemWriter)
                .taskExecutor(new SimpleAsyncTaskExecutor())
//                .throttleLimit(5)
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
