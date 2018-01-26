package com.jczc.operatorweb.service;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChargingStatisticService {
    public Map<String, Object> getStationStatisticByZoneId(Integer operatorId, Integer zoneId, int pageNum, int pageSize);
    public Map<String, Object> getStationStatisticByCityId(Integer operatorId, Integer cityId, int pageNum, int pageSize);
    public Map<String, Object> getStationStatisticByProvinceId(Integer operatorId, Integer provinceId, int pageNum, int pageSize);
    public Map<String, Object> getStationStatisticByRequireMent(Integer operatorId,StationStatisticRequireMent stationStatisticRequireMent, int pageNum, int pageSize);
    public Map<String, Object> getPileStatisticByStationId(Integer operatorId,Integer stationId, int pageNum, int pageSize);
    public Map<String, Object> getPileStatisticByZoneId(Integer operatorId, Integer zoneId, int pageNum, int pageSize);
    public Map<String, Object> getPileStatisticByCityId(Integer operatorId, Integer cityId, int pageNum, int pageSize);
    public Map<String, Object> getPileStatisticByProvinceId(Integer operatorId, Integer provinceId, int pageNum, int pageSize);
    public Map<String, Object> getPileStatisticByRequireMent(Integer operatorId, PileStatisticRequireMent pileStatisticRequireMent, int pageNum, int pageSize);
    public PageInfo<ChargingStatistic> getChargingStatisticByStationId(Integer operatorId, Integer stationId, int pageNum, int pageSize);
    public PageInfo<ChargingStatistic> getChargingStatisticByZoneId(Integer operatorId, Integer zoneId, int pageNum, int pageSize);
    public PageInfo<ChargingStatistic> getChargingStatisticByCityId(Integer operatorId, Integer cityId, int pageNum, int pageSize);
    public PageInfo<ChargingStatistic> getChargingStatisticByProvinceId(Integer operatorId, Integer provinceId, int pageNum, int pageSize);
    public PageInfo<ChargingStatistic> getChargingStatisticByRequireMent(Integer operatorId, ChargingStatisticRequireMent chargingStatisticRequireMent, int pageNum, int pageSize);
}
