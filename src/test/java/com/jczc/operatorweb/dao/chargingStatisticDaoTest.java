package com.jczc.operatorweb.dao;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.model.ChargingStatisticRequireMent;
import com.jczc.operatorweb.model.PileStatisticRequireMent;
import com.jczc.operatorweb.model.StationStatisticRequireMent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class chargingStatisticDaoTest {

    @Autowired
    ChargingStatisticDao chargingStatisticDao;

    @Test
    public void queryStationStatisticByZoneId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryStationStatisticByZoneId(1,330106)));
    }

    @Test
    public void queryStationStatisticByCityId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryStationStatisticByCityId(1,330100)));
    }
    @Test
    public void queryStationStatisticByProvinceId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryStationStatisticByProvinceId(1,330000)));
    }

    @Test
    public void queryStationStatisticByRequireMent() throws Exception {
        StationStatisticRequireMent stationStatisticRequireMent = new StationStatisticRequireMent();
        stationStatisticRequireMent.setProvinceId(330000);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2017-12-17 00:00:00");
        stationStatisticRequireMent.setEndTime(new Date());
        stationStatisticRequireMent.setStationName("石马");
        stationStatisticRequireMent.setBeginTime(date);
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryStationStatisticByRequireMent(1,stationStatisticRequireMent)));
    }
    @Test
    public void queryPileStatisticByStationId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryPileStatisticByStationId(1,1)));
    }
    @Test
    public void queryPileStatisticByZoneId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryPileStatisticByZoneId(1,330106)));
    }

    @Test
    public void queryPileStatisticByCityId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryPileStatisticByCityId(1,330100)));
    }

    @Test
    public void queryPileStatisticByProvinceId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryPileStatisticByProvinceId(1,330000)));
    }

    @Test
    public void queryPileStatisticByRequireMent() throws Exception {
        PileStatisticRequireMent pileStatisticRequireMent = new PileStatisticRequireMent();
        pileStatisticRequireMent.setPileName("3000000001");
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2017-12-18 00:00:00");
        pileStatisticRequireMent.setBeginTime(date);
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryPileStatisticByRequireMent(1, pileStatisticRequireMent)));
    }

    @Test
    public void queryChargingStatisticByOperatorId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryChargingStatisticByStationId(1,1)));
    }
    @Test
    public void queryChargingStatisticByZoneId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryChargingStatisticByZoneId(1,330106)));
    }

    @Test
    public void queryChargingStatisticByCityId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryChargingStatisticByCityId(1,330100)));
    }

    @Test
    public void queryChargingStatisticByProvinceId() throws Exception {
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryChargingStatisticByProvinceId(1,330000)));
    }

    @Test
    public void queryChargingStatisticByRequireMent() throws Exception {
        ChargingStatisticRequireMent chargingStatisticRequireMent = new ChargingStatisticRequireMent();
//        chargingStatisticRequireMent.setChargingUserName("test");
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2017-12-18 00:00:00");
        Date date2 = format.parse("2017-02-01 00:00:00");
        chargingStatisticRequireMent.setBeginTime(date);
//        chargingStatisticRequireMent.setEndTime(date2);
        System.out.println(JSON.toJSONString(chargingStatisticDao.queryChargingStatisticByRequireMent(1, chargingStatisticRequireMent)));
    }
}
