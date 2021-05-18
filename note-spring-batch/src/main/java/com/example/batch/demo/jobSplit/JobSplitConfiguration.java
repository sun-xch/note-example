package com.example.batch.demo.jobSplit;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class JobSplitConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobSplitTest(){
        return jobBuilderFactory.get("jobSplitTest")
                .start(flow1())
                .split(new SimpleAsyncTaskExecutor()).add(flow2())
                .end()
                .build();
    }

    @Bean
    public Flow flow1(){
        return new FlowBuilder<Flow>("flow1")
                .start(stepBuilderFactory.get("step1")
                        .tasklet(new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                System.out.println(Thread.currentThread().getName() + "===============step1============");
                                return RepeatStatus.FINISHED;
                            }
                        }).build())
                .build();
    }

    @Bean
    public Flow flow2() {
        return new FlowBuilder<Flow>("flow2")
                .start(stepBuilderFactory.get("step2")
                        .tasklet(new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                System.out.println(Thread.currentThread().getName() + "===============step2============");
                                return RepeatStatus.FINISHED;
                            }
                        }).build())
                .next(stepBuilderFactory.get("step3")
                        .tasklet(new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                System.out.println(Thread.currentThread().getName() + "===============step3============");
                                return RepeatStatus.FINISHED;
                            }
                        }).build())
                .build();
    }
}
