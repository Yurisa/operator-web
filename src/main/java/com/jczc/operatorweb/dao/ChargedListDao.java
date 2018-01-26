package com.jczc.operatorweb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jczc.operatorweb.model.ChargedListInfo;
@Repository
public interface ChargedListDao {
	public void addChargedList(ChargedListInfo chargedList);
	public int setParkingTime(@Param("pileNo")String pileNo,@Param("parkingTime")Date parkingTime);
	public int setStartTime(@Param("pileNo")String pileNo,@Param("startTime")Date startTime);
	public int setFullTime(@Param("pileNo")String pileNo,@Param("fullTime")Date fullTime);
	public int setEndTimeAndTotalElectricity(@Param("pileNo")String pileNo,@Param("endTime")Date endTime,@Param("totalElectricity")Double totalElectricity);
	public boolean setLeavingTime(@Param("pileNo")String pileNo,@Param("leavingTime")Date leavingTime);
	public ChargedListInfo getPileCurrentChargedList(@Param("pileNo")String pileNo);
	public void update(ChargedListInfo rec);
	public ChargedListInfo getUserCurrentChargedList(@Param("userId")Integer userId);
	public ChargedListInfo getUnstart(@Param("pileNo")String pileNo);
	
	public List<ChargedListInfo> getPileChargedList(@Param("pileNo")String pileNo,@Param("start")Date start,@Param("end")Date end);
	public List<ChargedListInfo> getUserChargedList(@Param("userId")Integer userId,@Param("start")Date start,@Param("end")Date end);
	public ChargedListInfo getChargedList(@Param("chargedListId")Long chargedListId);
	public void updateStatus(@Param("id")Long id, @Param("status")int status);
	
	
}
