//package com.example.batch.itemReaderDemo1.xmlFile;
//
//import com.example.business.entity.UserInfo;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//@Component("xmlFileDemoWriter")
//public class XmlFileDemoWriter implements ItemWriter<UserInfo> {
//    @Override
//    public void write(List<? extends UserInfo> items) throws Exception {
//        for (UserInfo  item : items){
//            System.out.println(item);
//        }
//        System.out.println("===========================xml文件当前批次结束==========================");
//    }
//}
