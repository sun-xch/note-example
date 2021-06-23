package com.example.batch.demo.jobParameters;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobParametersConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Tasklet helloWorldTasklet;

    @Bean
    public Job jobParametersTestJob() {
        return jobBuilderFactory.get("jobParametersTestJob")
                .start(jobParametersTestStep())
                .build();
    }

    @Bean
    public Step jobParametersTestStep() {
        return stepBuilderFactory.get("jobParametersTestStep")
                .tasklet(helloWorldTasklet)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet helloWorldTasklet(@Value("#{jobParameters['message']}") String message) {
        return (stepContribution, chunkContext) -> {
            System.out.println("参数信息是：" + message);
            return RepeatStatus.FINISHED;
        };
    }


}
