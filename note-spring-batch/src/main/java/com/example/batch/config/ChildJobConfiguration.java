//package com.example.batch.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class ChildJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job childJob1(){
//        return jobBuilderFactory.get("childJob1")
//                .start(step5_1())
//                .build();
//    }
//
//    @Bean
//    public Job childJob2(){
//        return jobBuilderFactory.get("childJob2")
//                .start(step5_2())
//                .build();
//    }
//
//    @Bean
//    public Step step5_1(){
//        return stepBuilderFactory.get("step5_1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("step5_1");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//    @Bean
//    public Step step5_2(){
//        return stepBuilderFactory.get("step5_2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("step5_2");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
//}
