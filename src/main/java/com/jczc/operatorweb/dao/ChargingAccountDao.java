package com.jczc.operatorweb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jczc.operatorweb.model.AccountDetail;
import com.jczc.operatorweb.model.ChargingFeeInfo;

@Repository
public interface ChargingAccountDao {
	List<ChargingFeeInfo> getChargingFeeByUserId(@Param("userId")Integer userId,@Param("start")Date start,@Param("end")Date end,@Param("status")Integer status);
	List<ChargingFeeInfo> getChargingFeeByPileNo(@Param("pileNo")String pileNo,@Param("start")Date start,@Param("end")Date end,@Param("status")Integer status);
	ChargingFeeInfo getChargingFeeByChargedListId(@Param("chargedListId")Long chargedListId);
	List<AccountDetail> getRechargeHistory(@Param("userId")Integer userId,@Param("start")Date start,@Param("end")Date end);
	
}
