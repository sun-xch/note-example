package com.example.batch.demo.jobParameters;

import com.example.business.entity.UserInfo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobParameters")
public class JobParametersController {

    @Autowired
	private JobLauncher jobLauncher;

    @Autowired
    private Job jobParametersTestJob;

    @PostMapping("/testParameters")
    public void testParameters(@RequestBody String message) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder()
						.addString("message", message)
						.toJobParameters();
		this.jobLauncher.run(jobParametersTestJob, jobParameters);
    }
}
