package com.test.AudioMedia.dao;

import com.test.AudioMedia.entity.TransportSegment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportSegmentDao {

    public int saveSegments(List<TransportSegment> segments);
}
