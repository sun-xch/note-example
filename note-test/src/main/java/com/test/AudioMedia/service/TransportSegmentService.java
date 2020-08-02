package com.test.AudioMedia.service;

import com.test.AudioMedia.entity.TransportSegment;
import com.test.AudioMedia.dao.TransportSegmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportSegmentService {

    @Autowired
    private TransportSegmentDao transportSegmentDao;

    public int saveSegments(List<TransportSegment> segments){
        return transportSegmentDao.saveSegments(segments);
    }
}
