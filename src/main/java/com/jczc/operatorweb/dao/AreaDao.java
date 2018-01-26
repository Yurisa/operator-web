package com.jczc.operatorweb.dao;

import com.jczc.operatorweb.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaDao {
    Area queryAreaById(Integer areaId);
    List<Area> queryAreaByParentId(Integer areaId);
}
