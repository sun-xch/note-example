package com.example.batch.demo;

import com.example.business.entity.UserInfo;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchProcessConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 批处理 步骤1 监听
     */
    @Autowired
    @Qualifier("batchProcessStep1Listener")
    private StepExecutionListener batchProcessStep1Listener;

    /**
     * 批处理 步骤1 批次监听
     */
    @Autowired
    @Qualifier("batchProcessStep1ChunkListener")
    private ChunkListener batchProcessStep1ChunkListener;

    /**
     * 批处理 步骤1 读监听
     */
    @Autowired
    @Qualifier("batchProcessStep1ReaderListener")
    private ItemReadListener<UserInfo> batchProcessStep1ReaderListener;

    /**
     * 批处理 步骤1 读
     */
    @Autowired
    @Qualifier("batchProcessStep1Reader")
    private ItemReader<UserInfo> batchProcessStep1Reader;

    /**
     * 批处理 步骤1 数据处理监听
     */
    @Autowired
    @Qualifier("batchProcessStep1ProcessorListener")
    private ItemProcessListener<UserInfo, UserInfo> batchProcessStep1ProcessorListener;

    /**
     * 批处理 步骤1 数据处理
     */
    @Autowired
    @Qualifier("batchProcessStep1Processor")
    private ItemProcessor<UserInfo, UserInfo> batchProcessStep1Processor;

    /**
     * 批处理步骤1 写监听
     */
    @Autowired
    @Qualifier("batchProcessStep1WriterListener")
    private ItemWriteListener<UserInfo> batchProcessStep1WriterListener;

    /**
     * 批处理步骤1 写
     */
    @Autowired
    @Qualifier("batchProcessStep1Writer")
    private ItemWriter<UserInfo> batchProcessStep1Writer;

    /**
     * 批处理任务
     * @return
     */
    @Bean
    public Job batchProcessJob(){
        return jobBuilderFactory.get("batchProcessJob")
                .start(batchProcessStep1())
                .build();
    }

    /**
     * 批处理 步骤1
     * @return
     */
    @Bean
    public Step batchProcessStep1(){
        return stepBuilderFactory.get("batchProcessStep1")
                .listener(batchProcessStep1Listener)
                .listener(batchProcessStep1ChunkListener)
                .<UserInfo, UserInfo>chunk(200)
                 .listener(batchProcessStep1ReaderListener)
                .reader(batchProcessStep1Reader)
                .listener(batchProcessStep1ProcessorListener)
                .processor(batchProcessStep1Processor)
                .listener(batchProcessStep1WriterListener)
                .writer(batchProcessStep1Writer)
                .build();
    }



}
