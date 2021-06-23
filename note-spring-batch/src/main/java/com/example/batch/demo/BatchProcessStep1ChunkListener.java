package com.example.batch.demo;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * 批处理步骤1 批次监听
 */
@Component("batchProcessStep1ChunkListener")
public class BatchProcessStep1ChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
        System.out.println("批次处理之前......");
    }

    @Override
    public void afterChunk(ChunkContext context) {
        System.out.println("批次处理之后......");
    }

    @Override
    public void afterChunkError(ChunkContext context) {

    }

}
