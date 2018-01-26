package com.jczc.operatorweb.service.Impl;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.model.StationAndPosition;
import com.jczc.operatorweb.service.PileStationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PileStationServiceImplTest {
    @Autowired
    PileStationService pileStationService;

    @Test
    public void getStationByLngLat() throws Exception {
        List<StationAndPosition> stationAndPositions = pileStationService.getStationByLngLat(120.8,30.1);
        System.out.println(JSON.toJSONString(stationAndPositions));
    }

}