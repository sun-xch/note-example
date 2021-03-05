package com.example.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.List;

public interface MessageHandler {
    boolean execute(List<CanalEntry.Entry> entrys) throws Exception;
}
