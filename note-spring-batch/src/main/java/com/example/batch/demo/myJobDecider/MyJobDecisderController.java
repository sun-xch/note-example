package com.example.batch.demo.myJobDecider;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myJobDecider")
public class MyJobDecisderController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job myjobDecisderTest;

    @PostMapping("/testMyJobDecider")
    public void testMyJobDecider() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        this.jobLauncher.run(myjobDecisderTest, new JobParameters());
    }
}
