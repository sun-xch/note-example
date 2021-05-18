//package com.example.batch.itemWriteDemo1;
//
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component("itemWriteDemo1")
//public class ItemWriteDemo1 implements ItemWriter<String> {
//    @Override
//    public void write(List<? extends String> items) throws Exception {
//        System.out.println("chunk size is: " + items.size());
//        for (String item : items){
//            System.out.println(item);
//        }
//    }
//}
