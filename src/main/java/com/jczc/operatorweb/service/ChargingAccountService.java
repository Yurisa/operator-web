package com.jczc.operatorweb.service;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.exception.DataException;
import com.jczc.operatorweb.model.AccountDetail;
import com.jczc.operatorweb.model.ChargedListInfo;
import com.jczc.operatorweb.model.ChargingFeeInfo;

public interface ChargingAccountService {
	/**
	 * 结算并返回充电费用，生成费用账单
	 * @param chargedList
	 * @return
	 */
	public ChargingFeeInfo createChargingFee(ChargedListInfo chargedList);
	/**
	 * 返回实时充电费用（不生成账单）
	 * @param chargedListInfo
	 * @return
	 */
	public ChargingFeeInfo getCurrentChargingFee(ChargedListInfo chargedListInfo);
	/**
	 * 分页查找用户的电费记录
	 * @param userId
	 * @param start
	 * @param end
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<ChargingFeeInfo> getChargingFeeListByUserId(Integer userId,Date start, Date end,Integer pageNum,Integer pageSize);
	/**
	 * 分页查找桩的电费记录
	 * @param pileNo
	 * @param start
	 * @param end
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<ChargingFeeInfo> getChargingFeeListByPileNo(String pileNo,Date start, Date end,Integer pageNum,Integer pageSize);
	/**
	 * 获取充电记录的电费信息
	 * @param chargedListId
	 * @return
	 */
	public ChargingFeeInfo getChargingFeeInfoByChargedListId(Long chargedListId);
	public void recharge(Integer userId, Double amount, String name, String wxOrderId, String wxUserId) throws DataException;
	/**
	 * 用户充值记录
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<AccountDetail> rechargeHistory(Integer userId, Date startTime, Date endTime, Integer pageNum,
			Integer pageSize);
}
