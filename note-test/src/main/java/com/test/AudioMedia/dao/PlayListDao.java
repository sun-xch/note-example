package com.test.AudioMedia.dao;

import com.test.AudioMedia.entity.PlayList;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListDao {

    public int savePlayList(PlayList playList);
}
