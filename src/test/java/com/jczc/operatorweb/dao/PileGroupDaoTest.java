package com.jczc.operatorweb.dao;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.entity.PileGroup;
import com.jczc.operatorweb.model.GroupRequireMent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PileGroupDaoTest {


    @Autowired
    PileGroupDao pileGroupDao;
    @Test
    public void save() throws Exception {
    }

    @Test
    public void queryPileGroupMessageById() throws Exception {
        System.out.println(JSON.toJSONString(pileGroupDao.queryPileGroupMessageById(1)));
    }

    @Test
    public void queryOperatorAllGroupResource() throws Exception {
        System.out.println(JSON.toJSONString(pileGroupDao.queryOperatorAllGroupResource(4)));
    }

    @Test
    public void queryPileGroupByStationId() throws Exception {
        System.out.println(JSON.toJSONString(pileGroupDao.queryPileGroupByStationId(1,1)));
    }

    @Test
    public void queryPileGroupByZoneId() throws Exception {
        System.out.println(JSON.toJSONString(pileGroupDao.queryPileGroupByZoneId(1,330107)));
    }

    @Test
    public void queryPileGroupBycityId() throws Exception {
        System.out.println(JSON.toJSONString(pileGroupDao.queryPileGroupByCityId(1,330100)));
    }
    @Test
    public void queryPileGroupByProvinceId() throws Exception {
        System.out.println(JSON.toJSONString(pileGroupDao.queryPileGroupByProvinceId(1,330000)));
    }
    @Test
    public void queryGroupStationMessageByStationId() throws Exception {
        System.out.println(JSON.toJSONString(pileGroupDao.queryGroupStationMessageByStationId(2,1)));
    }

    @Test
    public void queryGroupZoneMessageByZoneId() throws Exception {
        System.out.println(JSON.toJSONString(pileGroupDao.queryGroupZoneMessageByZoneId(2,330106)));
    }
    @Test
    public void queryGroupCityMessageByCityId() throws Exception {
        System.out.println(JSON.toJSONString(pileGroupDao.queryGroupCityMessageByCityId(2,330100)));
    }

    @Test
    public void queryGroupProvinceMessageByProvinceId() throws Exception {
        System.out.println(JSON.toJSONString(pileGroupDao.queryGroupProvinceMessageByProvinceId(2,330000)));
    }
    @Test
    public void queryGroupByRequirement() throws Exception {
        GroupRequireMent groupRequireMent = new GroupRequireMent();
        groupRequireMent.setZoneId(330106);
        groupRequireMent.setCityId(-1);
        groupRequireMent.setProvinceId(-1);
        groupRequireMent.setStatus(1);
        groupRequireMent.setGroupName("群");
//       groupRequireMent.setStationName("石马");
        System.out.println(JSON.toJSONString(pileGroupDao.queryGroupByRequirement(1,groupRequireMent)));
    }

    @Test
    public void queryByGroupId() throws Exception {
        System.out.printf(JSON.toJSONString(pileGroupDao.queryByGroupId(1)));
    }
    @Test
    public void updateByGroupId() throws Exception {
        PileGroup pileGroup = new PileGroup();
        pileGroup.setId(1);
        pileGroup.setName("充电群3");
        System.out.println(JSON.toJSONString(pileGroupDao.updateByGroupId(pileGroup)));
    }
}
