//package com.example.batch.itemReaderDemo1;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
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
//public class ItemReaderJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job ItemReaderDemoJob1(){
//        return jobBuilderFactory.get("ItemReaderDemoJob1")
//                .start(itemReaderDemo_1Step())
//                .build();
//
//    }
//
//    /**
//     * 简单的step 中使用tasklet
//     *           chunk使用reader writer
//     * @return
//     */
//    @Bean
//    public Step itemReaderDemo_1Step(){
//        return stepBuilderFactory.get("itemReaderDemo_1Step")
//                .<String, String>chunk(2)
//                .reader(itemReaderDemo())
//                .writer(list -> {
//                    for (String item : list){
//                        System.out.println("当前写出的是: " + item);
//                    }
//                }).build();
//    }
//
//    private itemReaderDemo1 itemReaderDemo() {
//        List<String> data = Arrays.asList("one","two","three");
//        return new itemReaderDemo1(data);
//    }
//
//
//}
