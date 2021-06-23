package com.example.cancal;

import com.example.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CanalInitHandler {

    private final static List<CanalClient> canalClientList = new ArrayList<>();

    @Autowired
    @Qualifier("messageHandlerToMongo")
    private MessageHandler messageHandlerToMongo;

    public void initCanalStart() {
        CanalClient client = new CanalClient(messageHandlerToMongo);
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
