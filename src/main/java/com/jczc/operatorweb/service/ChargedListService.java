package com.jczc.operatorweb.service;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.model.ChargedListInfo;
import com.jczc.operatorweb.model.ChargingDataInfo;
import com.jczc.operatorweb.model.ChargingFeeInfo;

public interface ChargedListService {
	
	/**
	 * 用户启动充电（扫描），下发开始充电指令
	 * @param chargedList
	 * @return
	 */
	public int startCharging(ChargedListInfo chargedList);
	/**
	 * 用户停止充电，下发关闭充电指令
	 * @param pileNo
	 * @param endTime
	 * @return
	 */
	public int endCharging(String pileNo,Date endTime);
	
	/**
	 * 停车消息
	 * @return
	 */
	public int carParkMsg();
	/**
	 * 枪插好消息
	 * @param pileNo
	 * @param endTime
	 * @return
	 */
	public int startChargingMsg(String pileNo,Date endTime);
	/**
	 * 电充满消息
	 * @param pileNo
	 * @param endTime
	 * @return
	 */
	public int chargingFullMsg(String pileNo,Date endTime);
	
	/**
	 * 车开离消息
	 * @return
	 */
	public int carLeaveMsg();
	
	/**
	 * 停止充电消息
	 * @param pileNo
	 * @param endTime
	 * @return
	 */
	public int stopChargingMsg(String pileNo,Date endTime);
	
	/**
	 * 根据主键获取充电记录
	 * @param chargedListId
	 * @return
	 */
	public ChargedListInfo getChargedList(Long chargedListId);
	/**
	 * 分页分时段获取桩的充电历史
	 * @param pileNo
	 * @param start
	 * @param end
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<ChargedListInfo> getPileChargedList(String pileNo,Date start,Date end, int pageNum, int pageSize);
	
	/**分页分时段获取用户的充电历史
	 * @param userId
	 * @param start
	 * @param end
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<ChargedListInfo> getUserChargedList(Integer userId,Date start,Date end, int pageNum, int pageSize);
	
	/**
	 * 获取用户当前充电记录
	 * @param pileNo
	 * @param userId
	 * @return
	 */
	public ChargedListInfo getCurrentChargedList(String pileNo,Integer userId);
	
	/**
	 * 分页获取充电记录对应的电量数据
	 * @param chargedListInfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<ChargingDataInfo> getHistoryChargingData(ChargedListInfo chargedListInfo,int pageNum, int pageSize);
	/**
	 * 获取指定用户的当前充电记录
	 * @param userId
	 * @return
	 */
	public ChargedListInfo getUserCurrentChargedList(Integer userId);
	/**
	 * 获取桩的当前充电记录
	 * @param pileNo
	 */
	public ChargedListInfo getPileCurrentChargedList(String pileNo);
	public void updateCurrentChargedListStatus(String pileNo, int status);

	
}
