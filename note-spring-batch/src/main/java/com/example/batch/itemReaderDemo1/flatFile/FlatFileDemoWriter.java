package com.example.batch.itemReaderDemo1.flatFile;

import com.example.business.entity.UserInfo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("flatFileDemoWriter")
public class FlatFileDemoWriter implements ItemWriter<UserInfo> {
    @Override
    public void write(List<? extends UserInfo> items) throws Exception {
        for (UserInfo item : items){
            System.out.println(item);
        }
        System.out.println("===========================当前批次结束==========================");
    }
}
