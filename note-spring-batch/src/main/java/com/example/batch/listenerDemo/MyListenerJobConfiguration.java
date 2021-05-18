//package com.example.batch.listenerDemo;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class MyListenerJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job myListenerJob(){
//        return jobBuilderFactory.get("myListenerJob")
//                .start(myListenerStep())
//                .listener(new MyJobListener())
//                .build();
//    }
//
//    @Bean
//    public Step myListenerStep() {
//        return stepBuilderFactory.get("myListenerStep")
//                .<String, String>chunk(2)
//                .faultTolerant()
//                .listener(new MyChunkListener())
//                .reader(reader())
//                .writer(writer())
//                .build();
//    }
//
//    @Bean
//    public ItemWriter<String> writer(){
//        return new ItemWriter<String>() {
//            @Override
//            public void write(List<? extends String> list) throws Exception {
//                for (String item : list){
//                    System.out.println("writing item: " + item);
//                }
//            }
//        };
//    }
//
//    @Bean
//    public ItemReader<String> reader(){
//        return new ListItemReader<>(Arrays.asList("first","second","third"));
//    }
//}
