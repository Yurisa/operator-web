package com.jczc.operatorweb.dao;

import com.jczc.operatorweb.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargingStatisticDao {
    public List<StationStatistic> queryStationStatisticByZoneId(@Param("operatorId") Integer operatorId, @Param("zoneId") Integer zoneId);
    public List<StationStatistic> queryStationStatisticByCityId(@Param("operatorId") Integer operatorId, @Param("cityId") Integer cityId);
    public List<StationStatistic> queryStationStatisticByProvinceId(@Param("operatorId") Integer operatorId, @Param("provinceId") Integer provinceId);
    public List<StationStatistic> queryStationStatisticByRequireMent(@Param("operatorId") Integer operatorId,@Param("stationStatisticRequireMent") StationStatisticRequireMent stationStatisticRequireMent);
    public List<PileStatistic> queryPileStatisticByStationId(@Param("operatorId") Integer operatorId, @Param("stationId") Integer stationId);
    public List<PileStatistic> queryPileStatisticByZoneId(@Param("operatorId") Integer operatorId, @Param("zoneId") Integer zoneId);
    public List<PileStatistic> queryPileStatisticByCityId(@Param("operatorId") Integer operatorId, @Param("cityId") Integer cityId);
    public List<PileStatistic> queryPileStatisticByProvinceId(@Param("operatorId") Integer operatorId, @Param("provinceId") Integer provinceId);
    public List<PileStatistic> queryPileStatisticByRequireMent(@Param("operatorId") Integer operatorId, @Param("pileStatisticRequireMent") PileStatisticRequireMent pileStatisticRequireMent);
    public List<ChargingStatistic> queryChargingStatisticByStationId(@Param("operatorId") Integer operatorId, @Param("stationId") Integer stationId);
    public List<ChargingStatistic> queryChargingStatisticByZoneId(@Param("operatorId") Integer operatorId, @Param("zoneId") Integer zoneId);
    public List<ChargingStatistic> queryChargingStatisticByCityId(@Param("operatorId") Integer operatorId, @Param("cityId") Integer cityId);
    public List<ChargingStatistic> queryChargingStatisticByProvinceId(@Param("operatorId") Integer operatorId, @Param("provinceId") Integer provinceId);
    public List<ChargingStatistic> queryChargingStatisticByRequireMent(@Param("operatorId") Integer operatorId, @Param("chargingStatisticRequireMent") ChargingStatisticRequireMent chargingStatisticRequireMent);
}
