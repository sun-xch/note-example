package com.example.batch.listenerDemo;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobListener implements JobExecutionListener {

    /**
     * job执行之前
     * @param jobExecution
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(jobExecution.getJobInstance().getJobName() + "执行之前......");
    }

    /**
     * job执行之后
     * @param jobExecution
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println(jobExecution.getJobInstance().getJobName() + "执行之后......");
    }
}
