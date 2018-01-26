package com.jczc.operatorweb.service.Impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.dao.PileStationDao;
import com.jczc.operatorweb.dao.PositionDao;
import com.jczc.operatorweb.entity.PileStation;
import com.jczc.operatorweb.entity.Position;
import com.jczc.operatorweb.model.*;
import com.jczc.operatorweb.service.PileStationService;
import com.jczc.operatorweb.service.PositionService;
import com.jczc.operatorweb.util.DistanceUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 */
@Service
public class PileStationServiceImpl implements PileStationService {

    @Autowired
    PileStationDao pileStationDao;

    @Autowired
    PositionService positionService;
    @Override
    public Map<String,Integer> addPileStationAndPosition(StationAndPosition stationAndPosition) {
        Map<String, Integer> resultMap = new HashMap<>();
        Position position = new Position();
        PileStation pileStation = new PileStation();
        BeanUtils.copyProperties(stationAndPosition, pileStation);
        BeanUtils.copyProperties(stationAndPosition, position);
        position.setCreateTime(new Date());
        pileStation.setCreateTime(new Date());
        Integer posIncremId = positionService.addPosition(position);
        pileStation.setPosId(posIncremId);
        Integer pileStationIncreId = pileStationDao.save(pileStation);
        resultMap.put("pileStationId", pileStationIncreId);
        resultMap.put("positionId", posIncremId);
        return resultMap;
    }

    @Override
    public Integer updateStationAndPosition(StationAndPosition stationAndPosition) {
        PileStation pileStation = new PileStation();
        Position position = new Position();
        BeanUtils.copyProperties(stationAndPosition, pileStation);
        BeanUtils.copyProperties(stationAndPosition, position);
        pileStation.setId(stationAndPosition.getStationId());
        position.setId(stationAndPosition.getPositionId());
        Integer success1 =  pileStationDao.update(pileStation);
        Integer success2 = positionService.updatePosition(position);
        if (success1 == 1 && success2 == 1){
            return 1;
        }
        return -1;
    }

    @Override
    public StationAndPosition getStationById(Integer stationId) {
        return pileStationDao.queryStationById(stationId);
    }

    @Override
    public PileStationMessage getPileStationMessageById(Integer stationId) {
        return pileStationDao.queryPileStationMessageById(stationId);
    }

    @Override
    public List<AreaAndStations> getAllAreaAndStations(Integer operatorId) {
        return pileStationDao.queryAllAreaAndStations(operatorId);
    }

    @Override
    public PageInfo<PileStationInfo> getAllPileStationsByOperatorId(Integer operatorId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PileStationInfo> pileStationInfos = pileStationDao.queryAllPileStationsByOperatorId(operatorId);
        return new PageInfo<>(pileStationInfos);
    }
    @Override
    public PileStationInfo getStationStatById(Integer stationId) {
        PileStationInfo pileStationInfo = pileStationDao.getPileStationStatById(stationId);
        return pileStationInfo;
    }

    @Override
    public Integer removeStationById(Integer stationId) {
        return pileStationDao.delete(stationId);
    }

    @Override
    public List<StationAndPosition> getStationByLngLat(double longitude,double latitude) {
        //先计算查询点的经纬度范围
        double r = 6371;//地球半径千米
        double dis = 10;//0.5千米距离
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minLat =latitude-dlat;
        double maxLat = latitude+dlat;
        double minLng = longitude -dlng;
        double maxLng = longitude + dlng;
        return pileStationDao.queryStationByLngLat(minLng, maxLng, minLat, maxLat);
    }


	@Override
	public List<StationAndPosition> getPileStationsForDistance(double lat, double lng, double distance) {
		List<StationAndPosition> allStations = pileStationDao.getPileStationsWithGps();
        List<StationAndPosition> inDistanceList = new ArrayList<>();
        double twoDistance = 0;
        for (StationAndPosition c :
        	allStations) {
            twoDistance = DistanceUtil.GetDistanceKM(lat, lng, c.getGpsLat(), c.getGpsLng());
            if (twoDistance <= (distance / 1000)) {
            	inDistanceList.add(c);
            }
        }
        return inDistanceList;
	}

    @Override
    public PageInfo<StationAndElectrity> findStationStatisticsByAreaId(Integer areaId, int pageNum, int pageSize, Date startTime, Date endTime, String stationName) {
        PageHelper.startPage(pageNum, pageSize);
        List<StationAndElectrity> stationAndElectrity=pileStationDao.getStationsWithChargingData(areaId,startTime,endTime,stationName);
        return new PageInfo<>(stationAndElectrity);
    }

}
