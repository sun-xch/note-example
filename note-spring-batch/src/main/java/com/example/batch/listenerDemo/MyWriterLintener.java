package com.example.batch.listenerDemo;

import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class MyWriterLintener implements ItemWriteListener {
    @Override
    public void beforeWrite(List items) {

    }

    @Override
    public void afterWrite(List items) {

    }

    @Override
    public void onWriteError(Exception exception, List items) {

    }
}
