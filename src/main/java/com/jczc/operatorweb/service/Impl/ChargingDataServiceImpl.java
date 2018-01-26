package com.jczc.operatorweb.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.dao.ChargingDataDao;
import com.jczc.operatorweb.model.ChargingDataInfo;
import com.jczc.operatorweb.service.ChargingDataService;
@Service
public class ChargingDataServiceImpl implements ChargingDataService {
	private static Logger logger = LoggerFactory.getLogger(ChargingDataService.class);
	@Autowired
	private ChargingDataDao chargingDataDao;
	public void addChargingData(ChargingDataInfo charingData){
		logger.info("充电数据 ChargingDataService.addChargingData："+JSON.toJSONString(charingData));
		chargingDataDao.addChargingData(charingData);
	}
}
