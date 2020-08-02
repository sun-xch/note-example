package com.test.AudioMedia.service;

import com.test.AudioMedia.entity.PlayList;
import com.test.AudioMedia.dao.PlayListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayListService {

    @Autowired
    private PlayListDao playListDao;

    public int savePlayList(PlayList playList){
        return playListDao.savePlayList(playList);
    }

}
