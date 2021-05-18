package com.example.batch.demo.jobTaskExecutor;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("taskExecutorItemWriter")
public class TaskExecutorItemWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        for (String item : items) {
            System.out.println(Thread.currentThread().getName() + "==writing item " + item);
        }
    }
}
