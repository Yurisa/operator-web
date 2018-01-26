package com.jczc.operatorweb.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.dao.PileDao;
import com.jczc.operatorweb.entity.Pile;
import com.jczc.operatorweb.model.PileInfo;
import com.jczc.operatorweb.service.PileService;
import com.jczc.operatorweb.util.DistanceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PileServiceImpl implements PileService {
	private static Logger logger= LoggerFactory.getLogger(PileServiceImpl.class);
    @Autowired
    PileDao pileDao;

    @Override
    public Integer addPile(Pile pile) {
        Integer incrementId = -1;
        if ( pileDao.save(pile) == 1){
            incrementId = pile.getId();
        }
        return incrementId;
    }

    @Override
    public Pile getPileById(Integer pileId) {
        return pileDao.queryById(pileId);
    }

    @Override
    public Integer updatePile(Pile pile) {
        return pileDao.updateById(pile);
    }

    @Override
    public PageInfo<PileInfo> getOperatorAllPiles(Integer operatorId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PileInfo> pileInfos = pileDao.queryOperatorAllPiles(operatorId);
        return new PageInfo<>(pileInfos);
    }

    @Override
    public PageInfo<PileInfo> getPileByBuildId(Integer stationId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PileInfo> pileInfos = pileDao.queryPileByBuildId(stationId);
        return new PageInfo<>(pileInfos);
    }

    @Override
    public PageInfo<PileInfo> getPileByGroupId(Integer groupId, int paheNum, int pageSize) {
        PageHelper.startPage(paheNum, pageSize);
        List<PileInfo> pileInfos = pileDao.queryPileByGroupId(groupId);
        return new PageInfo<>(pileInfos);
    }

    @Override
    public Integer removePileById(Integer pileId) {
        return pileDao.delete(pileId);
    }

	@Override
	public List<PileInfo> getPilesForDistance(double lat, double lng, double distance) {
		List<PileInfo> chargingPiles = pileDao.queryOperatorAllPiles(null);
		logger.info("threshold:"+distance);
        List<PileInfo> chargingPileList = new ArrayList<>();
        double twoDistance = 0;
        for (PileInfo c :
                chargingPiles) {
            twoDistance = DistanceUtil.GetDistanceKM(lat, lng, c.getGpsLat(), c.getGpsLng());
            logger.info("calc distance:src("+lat+","+lng+")"+"-->dest("+c.getGpsLat()+","+c.getGpsLng()+")="+twoDistance);
            if (twoDistance <= (distance / 1000)) {
                chargingPileList.add(c);
            }
        }
        return chargingPileList;
	}

	@Override
	public PileInfo getPileByNo(String pileNo) {
		return pileDao.getPileByNo(pileNo);
	}

    @Override
    public List<String> getAllPileIdentification() {
        return pileDao.getAllPileIdentification();
    }
}
