package com.jczc.operatorweb.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.dao.DeviceStateDao;
import com.jczc.operatorweb.dao.PileDao;
import com.jczc.operatorweb.entity.Pile;
import com.jczc.operatorweb.enums.ChargingStateEnum;
import com.jczc.operatorweb.enums.DeviceStateEnum;
import com.jczc.operatorweb.model.DeviceStateInfo;
import com.jczc.operatorweb.service.DeviceStateService;
@Service
public class DeviceStateServiceImpl implements DeviceStateService {

	@Autowired
	private DeviceStateDao deviceStateDao;
	@Autowired
	private PileDao pileDao;
	@Override
	public DeviceStateInfo getDeviceState(Long id) {
		return deviceStateDao.getDeviceState(id);
	}

	@Override
	public DeviceStateInfo getLatestDeviceState(String deviceNo) {
		return deviceStateDao.getLatestDeviceState(deviceNo);
	}

	@Override
	public void addDeviceState(DeviceStateInfo deviceState) {
		deviceStateDao.addDeviceState(deviceState);
		String deviceNo=deviceState.getDeviceNo();
		if(deviceNo.startsWith("3")){//充电桩
			Pile pile=new Pile();
			pile.setIdentification(deviceNo);
			pile.setDeviceStatus(DeviceStateEnum.getByCode(deviceState.getCode()).getDescribe());
			pileDao.updateByNo(pile);
		}else{
//			equipmentDao.updateByNo(equipment)
		}
		
	}

	@Override
	public void handleDeviceState(Long id, Integer handlerId, String remark) {
		deviceStateDao.handleDeviceState(id, handlerId, remark);
	}

	@Override
	public PageInfo<DeviceStateInfo> getDeviceStates(String deviceNo, Date startTime, Date endTime, int pageNum,
			int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<DeviceStateInfo> list=deviceStateDao.getHistoryDeviceStates(deviceNo, startTime, endTime);
		return new PageInfo<DeviceStateInfo>(list);
	}

}
