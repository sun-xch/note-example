//package com.example.batch.jobparametersDemo;
//
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.StepExecutionListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class MyStepListener implements StepExecutionListener {
//
//    private Map<String, JobParameter> parameters;
//
//    public Map<String, JobParameter> getParameters() {
//        return parameters;
//    }
//
//    public void setParameters(Map<String, JobParameter> parameters) {
//        this.parameters = parameters;
//    }
//
//    @Override
//    public void beforeStep(StepExecution stepExecution) {
//        parameters = stepExecution.getJobParameters().getParameters();
//    }
//
//    @Override
//    public ExitStatus afterStep(StepExecution stepExecution) {
//        return null;
//    }
//}
