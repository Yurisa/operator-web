package com.jczc.operatorweb.dao;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.entity.PileStation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PileStationDaoTest {

    @Autowired
    PileStationDao pileStationDao;

    @Test
    public void queryPileStationMessageById() throws Exception {
        System.out.println(JSON.toJSONString(pileStationDao.queryPileStationMessageById(1)));
    }

    @Test
    public void queryAllAreaAndStations() throws Exception {
        System.out.println(JSON.toJSONString(pileStationDao.queryAllAreaAndStations(1)));
    }

    @Test
    public void queryAllPileStationsByOperatorId() throws Exception {
        System.out.println(JSON.toJSONString(pileStationDao.queryAllPileStationsByOperatorId(1)));
    }

    @Test
    public void save() throws Exception {
        PileStation pileStation = new PileStation();
        pileStation.setCreateTime(new Date());
        pileStation.setDescription("浙大紫金港校区描述");
        pileStation.setCreatorId(1);
        pileStation.setPosId(1);
        pileStation.setParentId(0);
        pileStation.setName("浙大紫金港校区");
        pileStation.setType(1);
        pileStationDao.save(pileStation);
        System.out.println(pileStation.getId());
    }

    @Test
    public void queryStationById() throws Exception {
        System.out.println(JSON.toJSONString(pileStationDao.queryStationById(1)));
    }

    @Test
    public void update() throws Exception {
        PileStation pileStation = new PileStation();
        pileStation.setId(9);
        pileStation.setName("浙江科技学院小和山校区");
        pileStation.setDescription("浙江科技学院小和山校区描述");
        System.out.println(pileStationDao.update(pileStation));

    }
    @Test
    public void delete() throws Exception {
        System.out.println(pileStationDao.delete(20));
    }
}
