package com.jczc.operatorweb.service.Impl;

import com.jczc.operatorweb.dao.AreaDao;
import com.jczc.operatorweb.entity.Area;
import com.jczc.operatorweb.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaDao areaDao;

    @Override
    public Area getAreaById(Integer areaId) {
        return areaDao.queryAreaById(areaId);
    }

    @Override
    public List<Area> getAreaByParentId(Integer areaId) {
        return areaDao.queryAreaByParentId(areaId);
    }
}
