package com.example.batch.demo;

import com.example.business.entity.UserInfo;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("batchProcessStep1WriterListener")
public class BatchProcessStep1WriterListener implements ItemWriteListener<UserInfo> {

    @Override
    public void beforeWrite(List<? extends UserInfo> items) {
        System.out.println("步骤1写之前......");
    }

    @Override
    public void afterWrite(List<? extends UserInfo> items) {
        System.out.println("步骤1写之后......");
    }

    @Override
    public void onWriteError(Exception exception, List<? extends UserInfo> items) {
        System.out.println("步骤1写异常......");
    }
}
