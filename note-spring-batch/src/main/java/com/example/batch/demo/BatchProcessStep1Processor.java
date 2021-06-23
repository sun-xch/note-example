package com.example.batch.demo;

import com.example.business.entity.UserInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("batchProcessStep1Processor")
public class BatchProcessStep1Processor implements ItemProcessor<UserInfo, UserInfo>{

    @Override
    public UserInfo process(UserInfo item) throws Exception {
        return null;
    }
}
