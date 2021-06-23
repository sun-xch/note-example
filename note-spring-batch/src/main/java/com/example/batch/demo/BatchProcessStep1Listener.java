package com.example.batch.demo;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 批处理步骤1 监听
 */
@Component("batchProcessStep1Listener")
public class BatchProcessStep1Listener implements StepExecutionListener {

    /**
     * 步骤执行之前
     * @param stepExecution
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    /**
     * 步骤执行之后
     * @param stepExecution
     * @return
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
