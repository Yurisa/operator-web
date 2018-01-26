package com.jczc.operatorweb.service.Impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.dao.ChargedListDao;
import com.jczc.operatorweb.dao.ChargingDataDao;
import com.jczc.operatorweb.dao.ChargingPriceDao;
import com.jczc.operatorweb.dao.ChargingUserDao;
import com.jczc.operatorweb.dao.PileDao;
import com.jczc.operatorweb.entity.ChargingPrice;
import com.jczc.operatorweb.entity.Pile;
import com.jczc.operatorweb.enums.ChargingStateEnum;
import com.jczc.operatorweb.model.AccountDetail;
import com.jczc.operatorweb.model.ChargedFeeList;
import com.jczc.operatorweb.model.ChargedListInfo;
import com.jczc.operatorweb.model.ChargingDataInfo;
import com.jczc.operatorweb.model.ChargingFeeInfo;
import com.jczc.operatorweb.model.UserAccount;
import com.jczc.operatorweb.service.ChargedListService;
@Service
public class ChargedListServiceImpl implements ChargedListService {
	private static Logger logger = LoggerFactory.getLogger(ChargedListServiceImpl.class);
	@Autowired
	ChargedListDao chargedListDao;
	@Autowired
	ChargingDataDao chargingDataDao;
	@Autowired
	ChargingPriceDao chargingPriceDao;
	@Autowired
	ChargingUserDao chargingUserDao;
	@Autowired
	private PileDao pileDao;
	@Override
	public int startCharging(ChargedListInfo chargedList){
		//获取当前使用情况，有可能正在充电，有可能准备充电，有可能空闲
		ChargedListInfo rec=chargedListDao.getPileCurrentChargedList(chargedList.getChargingPileNo());
		
//		ChargedListInfo rec=chargedListDao.getUnstart(chargedList.getChargedPileNo());
		Pile pile=new Pile();
		pile.setIdentification(chargedList.getChargingPileNo());
		if(rec==null){
			chargedList.setStatus(2);
			chargedListDao.addChargedList(chargedList);
			pile.setChargingStatus(ChargingStateEnum.PARK_START.getName());
			pileDao.updateByNo(pile);
		}
		else if (rec.getChargingUserId() == null) {// 记录有消息3创建
			rec.setChargingBeginTime(new Date());
			BeanUtils.copyProperties(chargedList, rec,
					new String[] { "id", "createTime", "chargingBeginTime", "status" });
			chargedListDao.update(rec);
		}
		return 1;
	}
	@Override
	public int endCharging(String pileNo,Date time) {
		// TODO Auto-generated method stub
//		chargedListDao.setEndTimeAndTotalElectricity(pileNo, time,null);
		Pile pile=new Pile();
		pile.setIdentification(pileNo);
		pile.setChargingStatus(ChargingStateEnum.CHARGING_END.getName());
		return pileDao.updateByNo(pile);
		
	}
	@Override
	public int stopChargingMsg(String pileNo,Date time) {
		ChargedListInfo chargedList=chargedListDao.getPileCurrentChargedList(pileNo);
		List<ChargingDataInfo> dataList=chargingDataDao.getHistoryData(chargedList);
		double totalElectricity=0.0d;
		if(dataList!=null&&dataList.size()>0)
			totalElectricity=dataList.get(0).getElectricity();
		return chargedListDao.setEndTimeAndTotalElectricity(pileNo, time,totalElectricity);
	}
	@Override
	public int carParkMsg() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.jczc.operatorweb.service.ChargedListService#startChargingMsg(java.lang.String, java.util.Date)
	 * 先消息3再扫描
	 * 	桩号、创建时间、充电开始时间
	 * 先扫描在消息3
	 * 	桩号、创建时间、用户、车牌
	 */
	@Override
	public int startChargingMsg(String pileNo,Date time) {
		//收到状态3，充电枪插好事件
		//插枪和充电都可以用来创建新充电记录
		ChargedListInfo rec=chargedListDao.getPileCurrentChargedList(pileNo);
		if(rec==null){
			ChargedListInfo chargedList=new ChargedListInfo();
			chargedList.setChargingPileNo(pileNo);
			chargedList.setChargingBeginTime(time);
			chargedList.setCreateTime(new Date());
			chargedList.setStatus(3);
			chargedListDao.addChargedList(chargedList);
		}
		else if(rec.getChargingBeginTime()==null)//记录不是消息3创建的
			chargedListDao.setStartTime(pileNo, time);
		Pile pile=new Pile();
		pile.setIdentification(pileNo);
		pile.setChargingStatus(ChargingStateEnum.CHARGING_BEGIN.getName());
		return pileDao.updateByNo(pile);
	}
	@Override
	public int chargingFullMsg(String pileNo,Date time) {
		// TODO Auto-generated method stub
		chargedListDao.setFullTime(pileNo, time);
		Pile pile=new Pile();
		pile.setIdentification(pileNo);
		pile.setChargingStatus(ChargingStateEnum.CHARGING_FULL.getName());
		return pileDao.updateByNo(pile);
	}
	
	@Override
	public int carLeaveMsg() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public PageInfo<ChargedListInfo> getPileChargedList(String pileNo, Date start, Date end, int pageNum,
			int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChargedListInfo> list=chargedListDao.getPileChargedList(pileNo, start, end);
		return new PageInfo<>(list);
	}
	@Override
	public PageInfo<ChargedListInfo> getUserChargedList(Integer userId, Date start, Date end, int pageNum,
			int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChargedListInfo> list=chargedListDao.getUserChargedList(userId, start, end);
		return new PageInfo<>(list);
	}
	@Override
	public ChargedListInfo getCurrentChargedList(String pileNo, Integer userId) {
		ChargedListInfo info=chargedListDao.getPileCurrentChargedList(pileNo);
		if(info!=null&&info.getChargingUserId()==userId)
			return info;
		return null;
	}
	@Override
	public PageInfo<ChargingDataInfo> getHistoryChargingData(ChargedListInfo chargedListInfo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		if(chargedListInfo!=null){
			if(chargedListInfo.getChargingBeginTime()==null) return null;
			if(chargedListInfo.getChargingEndTime()==null)chargedListInfo.setChargingEndTime(new Date());
			List<ChargingDataInfo> list=chargingDataDao.getHistoryData(chargedListInfo);
			return new PageInfo<>(list);
		}
		return null;
	}
	@Override
	public ChargedListInfo getChargedList(Long chargedListId) {
		ChargedListInfo info=chargedListDao.getChargedList(chargedListId);
		return info;
	}
	@Override
	public ChargedListInfo getUserCurrentChargedList(Integer userId) {
		return chargedListDao.getUserCurrentChargedList(userId);
	}
	@Override
	public ChargedListInfo getPileCurrentChargedList(String pileNo) {
		return chargedListDao.getPileCurrentChargedList(pileNo);
	}
	@Override
	public void updateCurrentChargedListStatus(String pileNo, int status) {
		ChargedListInfo chargedListInfo=getPileCurrentChargedList(pileNo);
		if(chargedListInfo!=null&&chargedListInfo.getStatus()<status)
			chargedListDao.updateStatus(chargedListInfo.getId(),status);
			Pile pile=new Pile();
			pile.setIdentification(pileNo);
			pile.setChargingStatus(ChargingStateEnum.getByCode(status).getName());
			pileDao.updateByNo(pile);
	}
}
