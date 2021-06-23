package com.example.batch.listenerDemo;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.annotation.BeforeRead;

public class MyReaderListener implements ItemReadListener {

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Object item) {

    }

    @Override
    public void onReadError(Exception ex) {

    }
}
