package com.jczc.operatorweb.service;

import com.jczc.operatorweb.entity.Area;

import java.util.List;

public interface AreaService {
    Area getAreaById(Integer areaId);
    List<Area> getAreaByParentId(Integer areaId);
}
