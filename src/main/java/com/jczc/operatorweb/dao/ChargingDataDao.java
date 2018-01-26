package com.jczc.operatorweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jczc.operatorweb.model.ChargedListInfo;
import com.jczc.operatorweb.model.ChargingDataInfo;
@Repository
public interface ChargingDataDao {
	public void addChargingData(ChargingDataInfo data);
	public List<ChargingDataInfo> getHistoryData(ChargedListInfo chargedListInfo);
}
