package com.jczc.operatorweb.dao;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionDaoTest {

    @Autowired
    PositionDao positionDao;
    @Test
    public void save() throws Exception {
        Position position = new Position();
        position.setRegionId(330106);
        position.setDetailAddress("留和路318号");
        position.setCreatorId(1);
        position.setGpsLng(120.8);
        position.setGpsLat(30.6);
        position.setOrgId(1);
        position.setCreateTime(new Date());
        System.out.println(positionDao.save(position));
    }

    @Test
    public void update() throws Exception {
        Position position = new Position();
        position.setId(7);
        position.setDetailAddress("留和路319号");
        position.setGpsLat(30.1);
        System.out.println(positionDao.update(position));
    }

    @Test
    public void queryAllStationIdByZoneId() throws Exception {
        System.out.println(JSON.toJSONString(positionDao.queryAllStationIdByZoneId(330106)));
    }
}
