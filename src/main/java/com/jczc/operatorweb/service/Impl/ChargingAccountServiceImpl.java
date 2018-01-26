package com.jczc.operatorweb.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.dao.ChargedListDao;
import com.jczc.operatorweb.dao.ChargingAccountDao;
import com.jczc.operatorweb.dao.ChargingDataDao;
import com.jczc.operatorweb.dao.ChargingPriceDao;
import com.jczc.operatorweb.dao.ChargingUserDao;
import com.jczc.operatorweb.entity.ChargingPrice;
import com.jczc.operatorweb.exception.DataException;
import com.jczc.operatorweb.model.AccountDetail;
import com.jczc.operatorweb.model.ChargedFeeList;
import com.jczc.operatorweb.model.ChargedListInfo;
import com.jczc.operatorweb.model.ChargingDataInfo;
import com.jczc.operatorweb.model.ChargingFeeInfo;
import com.jczc.operatorweb.model.UserAccount;
import com.jczc.operatorweb.service.ChargingAccountService;
@Service
public class ChargingAccountServiceImpl implements ChargingAccountService{
	private static Logger logger= LoggerFactory.getLogger(ChargingAccountServiceImpl.class);
	@Autowired
	ChargingUserDao chargingUserDao;
	@Autowired
	ChargingDataDao chargingDataDao;
	@Autowired
	ChargingPriceDao chargingPriceDao;
	@Autowired
	ChargedListDao chargedListDao;
	@Autowired
	ChargingAccountDao chargingAccountDao;
	@Override
	public ChargingFeeInfo getCurrentChargingFee(ChargedListInfo chargedList) {
		logger.info("current charging list:"+JSON.toJSONString(chargedList));
		List<ChargingDataInfo> dataList=chargingDataDao.getHistoryData(chargedList);
		logger.info("current charging data count:"+dataList.size());
		ChargingPrice price=chargingPriceDao.getPileChargingPrice(chargedList.getChargingPileNo());
		if(dataList==null||price==null||dataList.size()==0)
			return null;
		
		ChargingFeeInfo chargingFeeInfo=new ChargingFeeInfo();
		chargingFeeInfo.setChargedListId(chargedList.getId());
		chargingFeeInfo.setUserId(chargedList.getChargingUserId());
		chargingFeeInfo.setPileNo(chargedList.getChargingPileNo());
		chargingFeeInfo.setStartTime(chargedList.getChargingBeginTime());
		chargingFeeInfo.setEndTime(new Date());
		chargingFeeInfo.setTotolElectricity(dataList.get(0).getElectricity());
		
		double money=calcTotalChargingFee(dataList,price);
		chargingFeeInfo.setMoney(money);
		chargingFeeInfo.setStatus(0);//当前充电为未付款
		return chargingFeeInfo;
	}
	
//	@Override
//	public ChargingFeeInfo createChargingFee(ChargedListInfo chargedList) {
//		
//		//生成电费账单
//		ChargingFeeInfo chargingFeeInfo=generateChargingBill(chargedList);
//		return chargingFeeInfo;
//	}
//	
	/**
	 * 根据充电记录的充电数据和电价生成充电账单（包括详单），返回总电费
	 * @param chargingDatasDESC
	 * @param price
	 * @return
	 */
	@Override
	public ChargingFeeInfo createChargingFee(ChargedListInfo chargedList) {
		//获取电价
		ChargingPrice price=chargingPriceDao.getPileChargingPrice(chargedList.getChargingPileNo());
		//获取充电数据
		List<ChargingDataInfo> chargingDatas=chargingDataDao.getHistoryData(chargedList);
		if(chargingDatas==null||price==null||chargingDatas.size()==0)
			return null;
		//获取用户账户
		UserAccount userAccount=chargingUserDao.getUserAccountByUserId(chargedList.getChargingUserId());
		if(userAccount==null)
			return null;
		ChargingFeeInfo chargingFeeInfo=new ChargingFeeInfo();
		chargingFeeInfo.setUserId(chargedList.getChargingUserId());
		chargingFeeInfo.setPileNo(chargedList.getChargingPileNo());
		chargingFeeInfo.setStartTime(chargedList.getChargingBeginTime());
		chargingFeeInfo.setEndTime(chargedList.getChargingEndTime());
		chargingFeeInfo.setChargedListId(chargedList.getId());
		chargingFeeInfo.setTotolElectricity(chargingDatas.get(0).getElectricity());
		AccountDetail detail=new AccountDetail();
		detail.setChangeTime(new Date());
		detail.setRemark(userAccount.getChargingUserId()+"的充电费用");
		detail.setPayStatus(0);//未支付
		detail.setType(2);//充电扣费
		detail.setAccountId(userAccount.getId());
		chargingUserDao.createUserAccountDetail(detail);//生成账户流水账单，金额为空
		
		double money=createChargedFeeList(chargedList,detail,chargingDatas,price);//生成账户流水账单的详单，返回电费
		chargingFeeInfo.setMoney(money);
		detail.setAmount(money);
		chargingUserDao.setAccountDetail(detail);//设置账户流水账单的金额
		
		return chargingFeeInfo;
	}
	private double createChargedFeeList(ChargedListInfo chargedList,AccountDetail detail,List<ChargingDataInfo> chargingDatas,ChargingPrice price) {
//		double money=calcChargingFee(chargingDatas, price);
		List<Map<String,Double>> listMap=calcMultiChargingFee(chargingDatas, price);
		double money=0.0d;
		for(Map<String,Double> item:listMap){
			ChargedFeeList chargedFee=new ChargedFeeList();
			chargedFee.setAccountDetailId(detail.getId());
			chargedFee.setChargedListId(chargedList.getId());
			chargedFee.setFee(item.get("money"));
			chargedFee.setCreateTime(new Date());
			chargedFee.setElectricity(item.get("electricity"));
			chargedFee.setFeeType(1);
			chargedFee.setRemark("充电电费");
			chargedFee.setStatus(0);//未支付
			chargingUserDao.createChargedFeeList(chargedFee);
			money+=item.get("money");
		}
		return money;
	}
	private double calcTotalChargingFee(List<ChargingDataInfo> chargingDatas,ChargingPrice price){
		List<Map<String,Double>> listMap=calcMultiChargingFee(chargingDatas, price);
		double money=0.0d;
		for(Map<String,Double> item:listMap)
			money+=item.get("money");
		return money;
	}
	private List<Map<String,Double>> calcMultiChargingFee(List<ChargingDataInfo> chargingDatas,ChargingPrice price){
		//分时计费方法，此处简单处理，只使用同一电价和最终电量
		double lastElectricity=chargingDatas.get(0).getElectricity();
		double uniPrice=price.getUniPrice();
		double money=lastElectricity * uniPrice;
		Map<String,Double> map=new HashMap<String,Double>();
		map.put("money", money);
		map.put("electricity", lastElectricity);
		List<Map<String,Double>> listMap=new ArrayList<Map<String,Double>>();
		listMap.add(map);
		return listMap;
	}
	@Override
	public PageInfo<ChargingFeeInfo> getChargingFeeListByUserId(Integer userId, Date start, Date end,Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChargingFeeInfo> list=chargingAccountDao.getChargingFeeByUserId(userId, start, end, null);
		return new PageInfo<>(list);
	}
	@Override
	public ChargingFeeInfo getChargingFeeInfoByChargedListId(Long chargedListId) {
		ChargingFeeInfo feeInfo=chargingAccountDao.getChargingFeeByChargedListId(chargedListId);
		return feeInfo;
	}
	@Override
	public PageInfo<ChargingFeeInfo> getChargingFeeListByPileNo(String pileNo,Date start, Date end, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChargingFeeInfo> list=chargingAccountDao.getChargingFeeByPileNo(pileNo, start, end, null);
		return new PageInfo<>(list);
	}

	@Override
	public void recharge(Integer userId, Double amount, String payFrom, String payOrderId, String payUserId)
			throws DataException {
		//修改账户余额
		UserAccount userAccount=chargingUserDao.getUserAccountByUserId(userId);
		chargingUserDao.recharge(userAccount.getBalance()+amount, userId);
		AccountDetail accountDetail=new AccountDetail();
		accountDetail.setAccountId(userAccount.getId());
		accountDetail.setAmount(amount);
		accountDetail.setChangeTime(new Date());
		accountDetail.setPayStatus(1);
		accountDetail.setType(1);//余额充值type为1
		accountDetail.setPayFrom(payFrom);
		accountDetail.setPayOrderId(payOrderId);
		accountDetail.setPayUserId(payUserId);
		chargingUserDao.createUserAccountDetail(accountDetail);
		//添加账户流水
		
	}

	@Override
	public PageInfo<AccountDetail> rechargeHistory(Integer userId, Date startTime, Date endTime, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<AccountDetail> list=chargingAccountDao.getRechargeHistory(userId, startTime, endTime);
		return new PageInfo<>(list);
	}
}
