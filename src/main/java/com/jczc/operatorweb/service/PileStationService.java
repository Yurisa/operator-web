package com.jczc.operatorweb.service;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.entity.PileStation;
import com.jczc.operatorweb.entity.Position;
import com.jczc.operatorweb.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PileStationService {
    public Map<String,Integer> addPileStationAndPosition(StationAndPosition stationAndPosition);
    public Integer updateStationAndPosition(StationAndPosition stationAndPosition);
    public StationAndPosition getStationById(Integer stationId);
    public PileStationMessage getPileStationMessageById(Integer stationId);
    public List<AreaAndStations> getAllAreaAndStations(Integer operatorId);
    public PageInfo<PileStationInfo> getAllPileStationsByOperatorId(Integer operatorId, int pageNum, int pageSize);
    public List<StationAndPosition> getStationByLngLat(double longitude,double latitude);
    public List<StationAndPosition> getPileStationsForDistance(double lat1, double lng1, double distance);
    public PageInfo<StationAndElectrity> findStationStatisticsByAreaId(Integer areaId, int pageNum, int pageSize, Date startTime, Date endTime, String stationName);
	public PileStationInfo getStationStatById(Integer stationId);
	public Integer removeStationById(Integer stationId);

}
