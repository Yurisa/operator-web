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
public class AreaDaoTest {
    @Autowired
    AreaDao areaDao;
    @Test
    public void queryAreaById() throws Exception {
        System.out.println(JSON.toJSONString(areaDao.queryAreaById(110000)));
    }

    @Test
    public void queryAreaByParentId() throws Exception {
        System.out.println(JSON.toJSONString(areaDao.queryAreaByParentId(110000)));
    }

}