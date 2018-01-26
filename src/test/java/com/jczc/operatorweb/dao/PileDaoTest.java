package com.jczc.operatorweb.dao;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.entity.Pile;
import com.jczc.operatorweb.model.PileInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PileDaoTest {

    @Autowired
    PileDao pileDao;
    @Test
    public void save() throws Exception {
        Pile pile  = new Pile("3000000007",2,1,"车位07",1, 4 , 120.07, 30.666,1, 2, "正常","正常");
        System.out.println(pileDao.save(pile));
        System.out.println(pile.getId());
    }

    @Test
    public void queryOperatorAllPiles() throws Exception {
        List<PileInfo> pileInfo = pileDao.queryOperatorAllPiles(4);
        System.out.println(pileInfo.size());
        System.out.println(JSON.toJSONString(pileInfo));
    }

    @Test
    public void queryPileByBuildId() throws Exception {
        System.out.println(JSON.toJSONString(pileDao.queryPileByBuildId(1)));
        System.out.println(pileDao.queryPileByBuildId(1).size());
    }

    @Test
    public void queryPileByGroupId() throws Exception {
        List<PileInfo> pileInfos = pileDao.queryPileByGroupId(1);
        System.out.println(pileInfos.size());
        System.out.println(JSON.toJSONString(pileInfos));
    }

    @Test
    public void updateById() throws Exception {
        Pile pile = new Pile();
        pile.setId(1027);
        pile.setDeviceStatus("异常");
        System.out.println(pileDao.updateById(pile));
    }

    @Test
    public void delete() throws Exception {
        System.out.println(pileDao.delete(1027));
    }

    @Test
    public void queryById() throws Exception {
        System.out.println(JSON.toJSONString(pileDao.queryById(1023)));
    }
    @Test
    public void getAllPileIdentification() throws Exception {
        System.out.println(JSON.toJSONString(pileDao.getAllPileIdentification()));
    }
}
