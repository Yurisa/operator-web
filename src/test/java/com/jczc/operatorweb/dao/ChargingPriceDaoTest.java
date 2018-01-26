package com.jczc.operatorweb.dao;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargingPriceDaoTest {

    @Autowired
    ChargingPriceDao chargingPriceDao;

    @Test
    public void queryAllChargingPrice() throws Exception {
        Integer operatorId = 1;
        System.out.println(JSON.toJSONString(chargingPriceDao.queryAllChargingPrice(operatorId)));
    }

}