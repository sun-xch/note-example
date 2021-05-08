package com.example.cancal;

import com.example.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CanalInitHandler {

    private final static List<CanalClient> canalClientList = new ArrayList<>();

    @Autowired
    private MessageHandler messageHandler;

    public void initCanalStart() {
        CanalClient client = new CanalClient(messageHandler);
        client.start();
        canalClientList.add(client);
    }

    @PreDestroy
    public void canalStop() {
        for (CanalClient canalClient : canalClientList) {
            canalClient.stop();
        }
    }
}
