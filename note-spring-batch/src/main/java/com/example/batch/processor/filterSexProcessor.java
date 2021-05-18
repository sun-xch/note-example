//package com.example.batch.processor;
//
//import com.example.business.entity.UserInfo;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.stereotype.Component;
//
//@Component
//public class filterSexProcessor implements ItemProcessor<UserInfo,UserInfo> {
//    @Override
//    public UserInfo process(UserInfo item) throws Exception {
//        if(Long.valueOf(item.getPhone()) % 2 == 0){
//            item.setSex("0");
//            return item;
//        }else{
//            return item;
//        }
//    }
//}
