package com.jczc.operatorweb.service.Impl;

import com.jczc.operatorweb.dao.PositionDao;
import com.jczc.operatorweb.entity.Position;
import com.jczc.operatorweb.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionDao positionDao;

    @Override
    public Integer addPosition(Position position) {
        Integer incrementId = -1;
        if ( positionDao.save(position) == 1){
            incrementId = position.getId();
        }
        return incrementId;
    }

    @Override
    public Integer updatePosition(Position position) {
        return positionDao.update(position);
    }


}
