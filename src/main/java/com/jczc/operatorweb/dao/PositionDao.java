package com.jczc.operatorweb.dao;

import com.jczc.operatorweb.entity.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionDao {
    public Integer save(Position position);
    public Integer update(Position position);
    public List<Integer> queryAllStationIdByZoneId(Integer zoneId);
}
