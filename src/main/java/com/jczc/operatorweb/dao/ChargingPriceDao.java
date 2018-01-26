package com.jczc.operatorweb.dao;


import com.jczc.operatorweb.entity.ChargingPrice;

import org.apache.ibatis.annotations.Param;
import com.jczc.operatorweb.model.ChargingPriceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargingPriceDao {
	public Integer save(ChargingPrice chargingprice);
	public Integer delete(Integer chargingpriceId);
	public Integer updateById(ChargingPrice ChargingPrice);
    public List<ChargingPriceInfo> queryAllChargingPrice(Integer operatorId);
    public ChargingPrice getPileChargingPrice(@Param("pileNo")String pileNo);
}
