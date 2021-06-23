package com.example.batch.demo;

import com.example.business.entity.UserInfo;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Component("batchProcessStep1ProcessorListener")
public class BatchProcessStep1ProcessorListener implements ItemProcessListener<UserInfo, UserInfo> {

    @Override
    public void beforeProcess(UserInfo item) {

    }

    @Override
    public void afterProcess(UserInfo item, UserInfo result) {

    }

    @Override
    public void onProcessError(UserInfo item, Exception e) {

    }

}
