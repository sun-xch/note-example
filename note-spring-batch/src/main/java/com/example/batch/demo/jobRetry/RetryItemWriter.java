package com.example.batch.demo.jobRetry;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("retryItemWriter")
public class RetryItemWriter implements ItemWriter<String> {

    private boolean retry = true;

    @Override
    public void write(List<? extends String> items) throws Exception {
        for (String item : items) {
            if(retry && item.indexOf("0") >= 0){
//                if(item.equals("10")){
//                    retry = true;
//                }else{
//                    retry = false;
//                }
                retry = false;
                System.out.println("Writing of item " + item + " failed");
                throw new RuntimeException(item + "写入异常");
            }else{
                retry = true;
                System.out.println("writing item " + item);
            }
        }
    }

}
