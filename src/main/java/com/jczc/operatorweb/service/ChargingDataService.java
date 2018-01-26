package com.jczc.operatorweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.dao.ChargingDataDao;
import com.jczc.operatorweb.model.ChargingDataInfo;

public interface ChargingDataService {
	public void addChargingData(ChargingDataInfo charingData);
}
