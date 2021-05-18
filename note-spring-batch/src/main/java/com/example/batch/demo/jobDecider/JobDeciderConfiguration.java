package com.example.batch.demo.jobDecider;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobDeciderConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobDeciderTest(){
        return jobBuilderFactory.get("jobDeciderTest")
                .start(deciderStep1())
                .on("COMPLETED").to(deciderStep2())
                .from(deciderStep2()).on("COMPLETED").to(deciderStep3())
                .from(deciderStep3()).end()
                .build();
    }

    @Bean
    public Step deciderStep1(){
        return stepBuilderFactory.get("deciderStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("=================deciderStep1");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step deciderStep2(){
        return stepBuilderFactory.get("deciderStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("=================deciderStep2");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step deciderStep3(){
        return stepBuilderFactory.get("deciderStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("===============deciderStep3");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
