package com.example.batch.demo.jobRestart;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableBatchProcessing
public class JobRestartConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobRestartTest() {
        return jobBuilderFactory.get("jobRestartTest")
                .start(jobRestartStep1())
                .next(jobRestartStep2())
                .build();
    }

    @Bean
    public Step jobRestartStep1() {
        return stepBuilderFactory.get("jobRestartStep1")
                .tasklet(restartTasklet())
                .build();
    }

    @Bean
    public Step jobRestartStep2() {
        return stepBuilderFactory.get("jobRestartStep2")
                .tasklet(restartTasklet())
                .build();
    }

    @Bean
    @StepScope
    public Tasklet restartTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                Map<String, Object> stepExecutionContext = chunkContext.getStepContext().getStepExecutionContext();
                //System.out.println(stepExecutionContext);
                System.out.println("是否包含key: " + stepExecutionContext.containsKey("flag"));
                if(stepExecutionContext.containsKey("flag")) {
                    System.out.println("任务继续执行...结束");
                    return RepeatStatus.FINISHED;
                } else {
                    System.out.println("停止服务...");
                    chunkContext.getStepContext().getStepExecution().getExecutionContext().put("flag", true);
                    throw new RuntimeException("运行异常");
                }
            }
        };
    }
}
