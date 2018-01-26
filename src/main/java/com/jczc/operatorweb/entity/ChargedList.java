package com.jczc.operatorweb.entity;

import java.util.Date;

public class ChargedList {
	private Long id;
	private Date createTime;
	private Date chargingBeginTime;
	private Date chargingFullTime;
	private Date chargingEndTime;
	private Date parkingBegenTime;
	private Date parkingEndTime;
	private String chargedPileNo;
	private String vehicleNo;
	private Integer chargingUserId;
	private Integer status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getChargingBeginTime() {
		return chargingBeginTime;
	}
	public void setChargingBeginTime(Date chargingBeginTime) {
		this.chargingBeginTime = chargingBeginTime;
	}
	public Date getChargingFullTime() {
		return chargingFullTime;
	}
	public void setChargingFullTime(Date chargingFullTime) {
		this.chargingFullTime = chargingFullTime;
	}
	public Date getChargingEndTime() {
		return chargingEndTime;
	}
	public void setChargingEndTime(Date chargingEndTime) {
		this.chargingEndTime = chargingEndTime;
	}
	public Date getParkingBegenTime() {
		return parkingBegenTime;
	}
	public void setParkingBegenTime(Date parkingBegenTime) {
		this.parkingBegenTime = parkingBegenTime;
	}
	public Date getParkingEndTime() {
		return parkingEndTime;
	}
	public void setParkingEndTime(Date parkingEndTime) {
		this.parkingEndTime = parkingEndTime;
	}
	public String getChargedPileNo() {
		return chargedPileNo;
	}
	public void setChargedPileNo(String chargedPileNo) {
		this.chargedPileNo = chargedPileNo;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public Integer getChargingUserId() {
		return chargingUserId;
	}
	public void setChargingUserId(Integer chargingUserId) {
		this.chargingUserId = chargingUserId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
