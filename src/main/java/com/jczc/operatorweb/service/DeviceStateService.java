package com.jczc.operatorweb.service;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.model.DeviceStateInfo;

public interface DeviceStateService {
	/**
	 * 获取设备（异常）状态详情
	 * @param id 设备状态id
	 * @return
	 */
	public DeviceStateInfo getDeviceState(Long id);
	/**
	 * 获取设备的当前状态
	 * @param deviceNo
	 * @return
	 */
	public DeviceStateInfo getLatestDeviceState(String deviceNo);
	/**
	 * 新增设备（异常）状态，并修改设备的当前状态
	 * @param deviceState
	 */
	public void addDeviceState(DeviceStateInfo deviceState);
	/**
	 * 处理设备（异常）状态：设置为已处理，可备注
	 * @param id
	 * @param handlerId
	 * @param remark
	 */
	public void handleDeviceState(Long id,Integer handlerId,String remark);
	/**
	 * 获取设备（异常）状态历史数据
	 * @param deviceNo
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<DeviceStateInfo> getDeviceStates(String deviceNo,Date startTime,Date endTime,int pageNum,int pageSize);
}
