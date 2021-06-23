package com.example.batch.demo;

import com.example.business.entity.UserInfo;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component("batchProcessStep1ReaderListener")
public class BatchProcessStep1ReaderListener implements ItemReadListener<UserInfo> {

    @Override
    public void beforeRead() {
        System.out.println("步骤1读之前.....");
    }

    @Override
    public void afterRead(UserInfo item) {
        System.out.println("步骤1读之后.....");
    }

    @Override
    public void onReadError(Exception ex) {
        System.out.println("步骤1读异常.....");
    }

}
