//package com.example.batch.itemWriteDemo1;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class ItemWriteJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    @Qualifier("itemWriteDemo1")
//    private ItemWriter<? super String> itemWriteDemo1;
//
//    @Bean
//    public Job itemWriteJob(){
//        return jobBuilderFactory.get("jobBuilderFactory")
//                .start(itemWriteStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemWriteStep() {
//        return stepBuilderFactory.get("itemWriteStep")
//                .<String, String>chunk(10)
//                .reader(itemReaderDemo1())
//                .writer(itemWriteDemo1)
//                .build();
//    }
//
//    @Bean
//    public ListItemReader<String> itemReaderDemo1() {
//        List<String> items = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            items.add("元素："+ i);
//        }
//        return new ListItemReader<>(items);
//    }
//
//}
