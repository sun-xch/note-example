package com.example.batch.demo.jobSkip;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("skipItemWriter")
public class SkipItemWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        for (String item : items) {
            if(item.indexOf("7") >= 0){
                System.out.println("Writing of item " + item + " failed");
                throw new RuntimeException(item + "写入异常");
            }else{
                System.out.println("writing item " + item);
            }
        }
    }
}
