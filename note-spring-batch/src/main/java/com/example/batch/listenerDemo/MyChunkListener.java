//package com.example.batch.listenerDemo;
//
//import org.springframework.batch.core.annotation.AfterChunk;
//import org.springframework.batch.core.annotation.BeforeChunk;
//import org.springframework.batch.core.scope.context.ChunkContext;
//
//public class MyChunkListener {
//
//    /**
//     * 批处理之前
//     * @param chunkContext
//     */
//    @BeforeChunk
//    public void beforeChunk(ChunkContext chunkContext) {
//        System.out.println(chunkContext.getStepContext().getStepName() + "批处理之前......");
//    }
//
//    /**
//     * 批处理之后
//     * @param chunkContext
//     */
//    @AfterChunk
//    public void afterChunk(ChunkContext chunkContext) {
//        System.out.println(chunkContext.getStepContext().getStepName() + "批处理之后......");
//    }
//}
