package com.example.batch.demo.myJobDecider;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 决策器 使用
 */
@Configuration
@EnableBatchProcessing
public class MyJobDecisderConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job myjobDecisderTest(){
        return jobBuilderFactory.get("myjobDecisderTest")
                .start(myDeciderStep1())
                .next(myDecider())
                .from(myDecider()).on("EVEN").to(myDeciderStep2())
                .from(myDecider()).on("ODD").to(myDeciderStep3())
                .from(myDeciderStep3()).on("*").to(myDecider())
                .end()
                .build();
    }

    @Bean
    public JobExecutionDecider myDecider(){
        return new MyDecider();
    }

    @Bean
    public Step myDeciderStep1(){
        return stepBuilderFactory.get("myDeciderStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("==================myDeciderStep1");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step myDeciderStep2(){
        return stepBuilderFactory.get("myDeciderStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("================myDeciderStep2");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step myDeciderStep3(){
        return stepBuilderFactory.get("myDeciderStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("==================myDeciderStep3");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
