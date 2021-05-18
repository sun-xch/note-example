//package com.example.batch.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.JobRegistry;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.JobStepBuilder;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.job.JobStep;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class ParentJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private Job childJob1;
//
//    @Autowired
//    private Job childJob2;
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//
//    @Bean
//    public Job parentJob5(JobRepository jobRepository, PlatformTransactionManager transactionManager){
//        return jobBuilderFactory.get("parentJob5")
//                .start(childJob1(jobRepository, transactionManager))
//                .next(childJob2(jobRepository, transactionManager))
//                .build();
//
//    }
//
//    // return JobStep
//    private Step childJob1(JobRepository jobRepository, PlatformTransactionManager transactionManager){
//        return new JobStepBuilder(new StepBuilder("childJob1"))
//                .job(childJob1)
//                .launcher(jobLauncher)
//                .repository(jobRepository)
//                .transactionManager(transactionManager)
//                .build();
//    }
//
//    private Step childJob2(JobRepository jobRepository, PlatformTransactionManager transactionManager){
//        return new JobStepBuilder(new StepBuilder("childJob2"))
//                .job(childJob2)
//                .launcher(jobLauncher)
//                .repository(jobRepository)
//                .transactionManager(transactionManager)
//                .build();
//    }
//
//}
