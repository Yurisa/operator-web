package com.jczc.operatorweb.service;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.entity.ChargingPrice;
import com.jczc.operatorweb.model.ChargingPriceInfo;

import java.util.List;


public interface ChargingPriceService {
	public Integer addChargingPrice(ChargingPrice chargingprice);
	public Integer removeChargingPriceById(Integer chargingpriceId);
	public Integer updateChargingPrice(ChargingPrice chargingprice);
    public PageInfo<ChargingPriceInfo> getAllChargingPrice(Integer operatorId, int pageNum, int pageSize);
    public List<ChargingPriceInfo> getTotalChargingPrice(Integer operatorId);
	public ChargingPrice getPileChargingPrice(String chargingPileNo);
}
