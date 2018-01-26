package com.jczc.operatorweb.model;

import java.util.Date;


public class PileChargingState {
	private String pileNo;
	private int code;
	private long receiveTime;
	//开始充电时间
	private Date chargingBeginTime;
	//停止充电时间
	private Date gunOffTime;
	//驶离时间
	private Date parkingOffTime;
	//车牌信息
	private String plateNumber;
	//电流
	private Double current;
	//电量
	private Double electricity;
	//功率
	private Double power;
	//电压
	private Double voltage;
	public String getPileNo() {
		return pileNo;
	}
	public void setPileNo(String pileNo) {
		this.pileNo = pileNo;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public long getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}
	public Date getChargingBeginTime() {
		return chargingBeginTime;
	}
	public void setChargingBeginTime(Date chargingBeginTime) {
		this.chargingBeginTime = chargingBeginTime;
	}
	public Date getGunOffTime() {
		return gunOffTime;
	}
	public void setGunOffTime(Date gunOffTime) {
		this.gunOffTime = gunOffTime;
	}
	public Date getParkingOffTime() {
		return parkingOffTime;
	}
	public void setParkingOffTime(Date parkingOffTime) {
		this.parkingOffTime = parkingOffTime;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public Double getCurrent() {
		return current;
	}
	public void setCurrent(Double current) {
		this.current = current;
	}
	public Double getElectricity() {
		return electricity;
	}
	public void setElectricity(Double electricity) {
		this.electricity = electricity;
	}
	public Double getPower() {
		return power;
	}
	public void setPower(Double power) {
		this.power = power;
	}
	public Double getVoltage() {
		return voltage;
	}
	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}
	
}
