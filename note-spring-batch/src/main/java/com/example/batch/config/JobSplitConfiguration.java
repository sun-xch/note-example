//package com.example.batch.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//
///**
// * 通过 split 开启异步多线程的步骤执行
// */
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class JobSplitConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job jobSplitDemo3(){
//        return jobBuilderFactory.get("jobSplitDemo3")
//                .start(splitFlow1())
//                .split(new SimpleAsyncTaskExecutor()).add(splitFlow2())
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Flow splitFlow1(){
//        return new FlowBuilder<Flow>("splitFlow1")
//                .start(stepBuilderFactory.get("step3_1").tasklet(tasklet()).build()).build();
//    }
//
//    @Bean
//    public Flow splitFlow2(){
//        return new FlowBuilder<Flow>("splitFlow2")
//                .start(stepBuilderFactory.get("step3_2").tasklet(tasklet()).build())
//                .next(stepBuilderFactory.get("step3_3").tasklet(tasklet()).build())
//                .build();
//    }
//
//    private Tasklet tasklet(){
//        return new PrintTasklet();
//    }
//
//    private class PrintTasklet implements Tasklet{
//
//        @Override
//        public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//            System.out.println(String.format("步骤:%s,线程:%s",chunkContext.getStepContext().getStepName(),Thread.currentThread().getName()));
//            return RepeatStatus.FINISHED;
//        }
//    }
//
//}
