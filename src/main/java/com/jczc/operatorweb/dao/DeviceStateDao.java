package com.jczc.operatorweb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jczc.operatorweb.model.DeviceStateInfo;
@Repository
public interface DeviceStateDao {
	public void addDeviceState(DeviceStateInfo deviceStateInfo);
	public DeviceStateInfo getDeviceState(@Param("id")Long id);
	public void handleDeviceState(@Param("id")Long id,@Param("handlerId")Integer handlerId,@Param("remark")String remark);
	public DeviceStateInfo getLatestDeviceState(@Param("deviceNo")String deviceNo);
	public List<DeviceStateInfo> getHistoryDeviceStates(@Param("deviceNo")String deviceNo,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
}
