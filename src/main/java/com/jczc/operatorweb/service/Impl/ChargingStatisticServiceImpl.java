package com.jczc.operatorweb.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.dao.ChargingStatisticDao;
import com.jczc.operatorweb.model.*;
import com.jczc.operatorweb.service.ChargingStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChargingStatisticServiceImpl implements ChargingStatisticService {
    @Autowired
    ChargingStatisticDao chargingStatisticDao;

    @Override
    public Map<String, Object> getStationStatisticByZoneId(Integer operatorId, Integer zoneId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Integer allPileNum = 0;
        Integer allChargingNum = 0;
        double  allChargingHours = 0.0;
        double allChargingElectricity = 0.0;
        double allChargingPrice = 0.0;
        Map<String, Object> map = new HashMap<>();
        List<StationStatistic> stationStatistics = chargingStatisticDao.queryStationStatisticByZoneId(operatorId, zoneId);
        for (StationStatistic s : stationStatistics){
            allPileNum += s.getTotalPileNum();
            allChargingNum += s.getTotalChargingNum();
            allChargingHours += s.getTotalChargingHours();
            allChargingElectricity += s.getTotalChargingElectricity();
            allChargingPrice += s.getTotalChargingPrice();
        }
        map.put("page", new PageInfo<>(stationStatistics));
        map.put("allPileNum", allPileNum);
        map.put("allChargingNum", allChargingNum);
        map.put("allChargingHours", allChargingHours);
        map.put("allChargingElectricity", allChargingElectricity);
        map.put("allChargingPrice", allChargingPrice);
        return map;
    }

    @Override
    public Map<String, Object> getStationStatisticByCityId(Integer operatorId, Integer cityId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Integer allPileNum = 0;
        Integer allChargingNum = 0;
        double  allChargingHours = 0.0;
        double allChargingElectricity = 0.0;
        double allChargingPrice = 0.0;
        Map<String, Object> map = new HashMap<>();
        List<StationStatistic> stationStatistics = chargingStatisticDao.queryStationStatisticByCityId(operatorId, cityId);
        for (StationStatistic s : stationStatistics){
            allPileNum += s.getTotalPileNum();
            allChargingNum += s.getTotalChargingNum();
            allChargingHours += s.getTotalChargingHours();
            allChargingElectricity += s.getTotalChargingElectricity();
            allChargingPrice += s.getTotalChargingPrice();
        }
        map.put("page", new PageInfo<>(stationStatistics));
        map.put("allPileNum", allPileNum);
        map.put("allChargingNum", allChargingNum);
        map.put("allChargingHours", allChargingHours);
        map.put("allChargingElectricity", allChargingElectricity);
        map.put("allChargingPrice", allChargingPrice);
        return map;
    }

    @Override
    public Map<String, Object> getStationStatisticByProvinceId(Integer operatorId, Integer provinceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Integer allPileNum = 0;
        Integer allChargingNum = 0;
        double  allChargingHours = 0.0;
        double allChargingElectricity = 0.0;
        double allChargingPrice = 0.0;
        Map<String, Object> map = new HashMap<>();
        List<StationStatistic> stationStatistics = chargingStatisticDao.queryStationStatisticByProvinceId(operatorId, provinceId);
        for (StationStatistic s : stationStatistics){
            allPileNum += s.getTotalPileNum();
            allChargingNum += s.getTotalChargingNum();
            allChargingHours += s.getTotalChargingHours();
            allChargingElectricity += s.getTotalChargingElectricity();
            allChargingPrice += s.getTotalChargingPrice();
        }
        map.put("page", new PageInfo<>(stationStatistics));
        map.put("allPileNum", allPileNum);
        map.put("allChargingNum", allChargingNum);
        map.put("allChargingHours", allChargingHours);
        map.put("allChargingElectricity", allChargingElectricity);
        map.put("allChargingPrice", allChargingPrice);
        return map;
    }

    @Override
    public Map<String, Object> getStationStatisticByRequireMent(Integer operatorId, StationStatisticRequireMent stationStatisticRequireMent, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Integer allPileNum = 0;
        Integer allChargingNum = 0;
        double  allChargingHours = 0.0;
        double allChargingElectricity = 0.0;
        double allChargingPrice = 0.0;
        Map<String, Object> map = new HashMap<>();
        List<StationStatistic> stationStatistics = chargingStatisticDao.queryStationStatisticByRequireMent(operatorId, stationStatisticRequireMent);
        for (StationStatistic s : stationStatistics){
            allPileNum += s.getTotalPileNum();
            allChargingNum += s.getTotalChargingNum();
            allChargingHours += s.getTotalChargingHours();
            allChargingElectricity += s.getTotalChargingElectricity();
            allChargingPrice += s.getTotalChargingPrice();
        }
        map.put("page", new PageInfo<>(stationStatistics));
        map.put("allPileNum", allPileNum);
        map.put("allChargingNum", allChargingNum);
        map.put("allChargingHours", allChargingHours);
        map.put("allChargingElectricity", allChargingElectricity);
        map.put("allChargingPrice", allChargingPrice);
        return map;
    }

    @Override
    public Map<String, Object> getPileStatisticByStationId(Integer operatorId, Integer stationId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Integer allChargingNum = 0;
        double  allChargingHours = 0.0;
        double allChargingElectricity = 0.0;
        double allChargingPrice = 0.0;
        Map<String, Object> map = new HashMap<>();
        List<PileStatistic> pileStatistics = chargingStatisticDao.queryPileStatisticByStationId(operatorId, stationId);
        for(PileStatistic p : pileStatistics){
            allChargingNum += p.getTotalChargingNum();
            allChargingHours += p.getTotalChargingHours();
            allChargingElectricity += p.getTotalChargingElectricity();
            allChargingPrice += p.getTotalChargingPrice();
        }
        map.put("page", new PageInfo<>(pileStatistics));
        map.put("allChargingNum", allChargingNum);
        map.put("allChargingHours", allChargingHours);
        map.put("allChargingElectricity", allChargingElectricity);
        map.put("allChargingPrice", allChargingPrice);
        return map;
    }

    @Override
    public Map<String, Object> getPileStatisticByZoneId(Integer operatorId, Integer zoneId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Integer allChargingNum = 0;
        double  allChargingHours = 0.0;
        double allChargingElectricity = 0.0;
        double allChargingPrice = 0.0;
        Map<String, Object> map = new HashMap<>();
        List<PileStatistic> pileStatistics = chargingStatisticDao.queryPileStatisticByZoneId(operatorId, zoneId);
        for(PileStatistic p : pileStatistics){
            allChargingNum += p.getTotalChargingNum();
            allChargingHours += p.getTotalChargingHours();
            allChargingElectricity += p.getTotalChargingElectricity();
            allChargingPrice += p.getTotalChargingPrice();
        }
        map.put("page", new PageInfo<>(pileStatistics));
        map.put("allChargingNum", allChargingNum);
        map.put("allChargingHours", allChargingHours);
        map.put("allChargingElectricity", allChargingElectricity);
        map.put("allChargingPrice", allChargingPrice);
        return map;
    }

    @Override
    public Map<String, Object> getPileStatisticByCityId(Integer operatorId, Integer cityId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Integer allChargingNum = 0;
        double  allChargingHours = 0.0;
        double allChargingElectricity = 0.0;
        double allChargingPrice = 0.0;
        Map<String, Object> map = new HashMap<>();
        List<PileStatistic> pileStatistics = chargingStatisticDao.queryPileStatisticByCityId(operatorId, cityId);
        for(PileStatistic p : pileStatistics){
            allChargingNum += p.getTotalChargingNum();
            allChargingHours += p.getTotalChargingHours();
            allChargingElectricity += p.getTotalChargingElectricity();
            allChargingPrice += p.getTotalChargingPrice();
        }
        map.put("page", new PageInfo<>(pileStatistics));
        map.put("allChargingNum", allChargingNum);
        map.put("allChargingHours", allChargingHours);
        map.put("allChargingElectricity", allChargingElectricity);
        map.put("allChargingPrice", allChargingPrice);
        return map;
    }

    @Override
    public Map<String, Object> getPileStatisticByProvinceId(Integer operatorId, Integer provinceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Integer allChargingNum = 0;
        double  allChargingHours = 0.0;
        double allChargingElectricity = 0.0;
        double allChargingPrice = 0.0;
        Map<String, Object> map = new HashMap<>();
        List<PileStatistic> pileStatistics = chargingStatisticDao.queryPileStatisticByProvinceId(operatorId, provinceId);
        for(PileStatistic p : pileStatistics){
            allChargingNum += p.getTotalChargingNum();
            allChargingHours += p.getTotalChargingHours();
            allChargingElectricity += p.getTotalChargingElectricity();
            allChargingPrice += p.getTotalChargingPrice();
        }
        map.put("page", new PageInfo<>(pileStatistics));
        map.put("allChargingNum", allChargingNum);
        map.put("allChargingHours", allChargingHours);
        map.put("allChargingElectricity", allChargingElectricity);
        map.put("allChargingPrice", allChargingPrice);
        return map;
    }

    @Override
    public  Map<String, Object> getPileStatisticByRequireMent(Integer operatorId, PileStatisticRequireMent pileStatisticRequireMent, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Integer allChargingNum = 0;
        double  allChargingHours = 0.0;
        double allChargingElectricity = 0.0;
        double allChargingPrice = 0.0;
        Map<String, Object> map = new HashMap<>();
        List<PileStatistic> pileStatistics = chargingStatisticDao.queryPileStatisticByRequireMent(operatorId, pileStatisticRequireMent);
        for(PileStatistic p : pileStatistics){
            allChargingNum += p.getTotalChargingNum();
            allChargingHours += p.getTotalChargingHours();
            allChargingElectricity += p.getTotalChargingElectricity();
            allChargingPrice += p.getTotalChargingPrice();
        }
        map.put("page", new PageInfo<>(pileStatistics));
        map.put("allChargingNum", allChargingNum);
        map.put("allChargingHours", allChargingHours);
        map.put("allChargingElectricity", allChargingElectricity);
        map.put("allChargingPrice", allChargingPrice);
        return map;
    }

    @Override
    public PageInfo<ChargingStatistic> getChargingStatisticByStationId(Integer operatorId, Integer stationId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChargingStatistic> chargingStatistics = chargingStatisticDao.queryChargingStatisticByStationId(operatorId, stationId);
        return new PageInfo<>(chargingStatistics);
    }

    @Override
    public PageInfo<ChargingStatistic> getChargingStatisticByZoneId(Integer operatorId, Integer zoneId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChargingStatistic> chargingStatistics = chargingStatisticDao.queryChargingStatisticByZoneId(operatorId, zoneId);
        return new PageInfo<>(chargingStatistics);
    }

    @Override
    public PageInfo<ChargingStatistic> getChargingStatisticByCityId(Integer operatorId, Integer cityId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChargingStatistic> chargingStatistics = chargingStatisticDao.queryChargingStatisticByCityId(operatorId, cityId);
        return new PageInfo<>(chargingStatistics);
    }

    @Override
    public PageInfo<ChargingStatistic> getChargingStatisticByProvinceId(Integer operatorId, Integer provinceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChargingStatistic> chargingStatistics = chargingStatisticDao.queryChargingStatisticByProvinceId(operatorId, provinceId);
        return new PageInfo<>(chargingStatistics);
    }

    @Override
    public PageInfo<ChargingStatistic> getChargingStatisticByRequireMent(Integer operatorId, ChargingStatisticRequireMent chargingStatisticRequireMent, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChargingStatistic> chargingStatistics = chargingStatisticDao.queryChargingStatisticByRequireMent(operatorId, chargingStatisticRequireMent);
        return new PageInfo<>(chargingStatistics);
    }
}
