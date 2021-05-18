//package com.example.batch.jobparametersDemo;
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
//public class JobParametersDemoConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private MyStepListener myStepListener;
//
//    @Bean
//    public Job myJobParameterDemoJob(){
//        return jobBuilderFactory.get("myJobParameterDemoJob")
//                .start(myJobParameterDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step myJobParameterDemoStep(){
//        return stepBuilderFactory.get("myJobParameterDemoStep")
//                .listener(myStepListener)
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("参数是: " + myStepListener.getParameters().get("info"));
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//
//    }
//
//}
