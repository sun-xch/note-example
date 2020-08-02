package com.test.AudioMedia.controller;

import com.test.AudioMedia.entity.PlayList;
import com.test.AudioMedia.entity.TransportSegment;
import com.test.AudioMedia.service.PlayListService;
import com.test.AudioMedia.service.TransportSegmentService;
import com.test.AudioMedia.task.DivideTask;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

@RestController
public class TransportSegmentController {

    @Autowired
    private PlayListService playListService;

    @Autowired
    private TransportSegmentService transportSegmentService;

    @RequestMapping("/createTransportSegment")
    public void createTransportSegment(){
        byte data[]=new byte[1024];
        String fileName = "D:\\test\\123.mp4";
        try {
            divideToSegments(fileName,"111222333444555666",data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 将文件切割成片
     * @param filename
     * @param uuid
     * @param data
     * @throws IOException
     */
    public void divideToSegments(String filename, String uuid, byte[] data) throws IOException {

        DivideTask divideTask = new DivideTask(filename,uuid,data);

        //创建一个 ThreadPoolExecutor 对象
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<ImmutablePair<PlayList, List<TransportSegment>>> divideFuture = executorService.submit(divideTask);

        String mediaId = String.format("media.%s",uuid);

        try {
            ImmutablePair<PlayList, List<TransportSegment>> plsAndTsFiles = divideFuture.get(30, TimeUnit.MINUTES);
            PlayList playlist = plsAndTsFiles.getLeft();
            List<TransportSegment> segments = plsAndTsFiles.getRight();

            //保存播放列表
            playListService.savePlayList(playlist);

            //保存切片文件
            transportSegmentService.saveSegments(segments);

            //放到缓存里 没有redis 所以不放了
            /*Map<String,String> mapping = new HashMap<>();
            mapping.put("playlist",playlist.getContext());
            //把原始文件放进去,方便以后下载
            mapping.put("binary", Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(filename))));

            for (TransportSegment segment:segments){
                String tsFileName = segment.getFilename();
                byte[] bytes = segment.getBytes();
                String binary = Base64.getEncoder().encodeToString(bytes);
                mapping.put(tsFileName,binary);
            }

            //切片以后的文件添加到缓存
            setCacheMap(mediaId, mapping);

            //30分钟以后失效
            expire(mediaId,7,TimeUnit.DAYS);*/

        } catch (InterruptedException| ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}
