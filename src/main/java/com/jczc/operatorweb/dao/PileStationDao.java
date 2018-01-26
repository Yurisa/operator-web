package com.jczc.operatorweb.dao;

import com.jczc.operatorweb.entity.PileStation;
import com.jczc.operatorweb.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface PileStationDao {
    public Integer save(PileStation pileStation);
    public Integer update(PileStation pileStation);
    public StationAndPosition queryStationById(Integer stationId);
    public PileStationMessage queryPileStationMessageById(Integer stationId);
    public List<AreaAndStations> queryAllAreaAndStations(Integer operatorId);
    public List<PileStationInfo> queryAllPileStationsByOperatorId(Integer operatorId);
    public List<StationAndPosition>  queryStationByLngLat(@Param("minLng") double minLng, @Param("maxLng") double maxLng, @Param("minLat") double minLat, @Param("maxLat") double maxLat);
    public List<StationAndPosition> getPileStationsWithGps();
    public List<StationAndElectrity> getStationsWithChargingData(Integer areaId,Date startTime,Date endTime,String stationId);
	public PileStationInfo getPileStationStatById(@Param("stationId")Integer stationId);
	public Integer delete(Integer stationId);
}
