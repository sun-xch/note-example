//package com.example.batch.itemReaderDemo1;
//
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//
//import java.util.Iterator;
//import java.util.List;
//
//public class itemReaderDemo1 implements ItemReader<String> {
//
//
//    private final Iterator<String> iterator;
//
//    public itemReaderDemo1(List<String> data) {
//        this.iterator = data.iterator();
//    }
//
//    @Override
//    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//
//        if(this.iterator.hasNext()){
//            return this.iterator.next();
//        }else {
//            return null;
//        }
//    }
//
//
//}
