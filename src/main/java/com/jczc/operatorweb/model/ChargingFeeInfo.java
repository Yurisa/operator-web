package com.jczc.operatorweb.model;

import java.util.Date;

public class ChargingFeeInfo {
	private Integer userId;//
	private String pileNo;//
	private Date startTime;//
	private Date endTime;//
	private Long chargedListId;//
	private Long accountDetailId;//
	private double totolElectricity;//
	private double money;//
	private Integer status;//
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPileNo() {
		return pileNo;
	}
	public void setPileNo(String pileNo) {
		this.pileNo = pileNo;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getChargedListId() {
		return chargedListId;
	}
	public void setChargedListId(Long chargedListId) {
		this.chargedListId = chargedListId;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getTotolElectricity() {
		return totolElectricity;
	}
	public void setTotolElectricity(double totolElectricity) {
		this.totolElectricity = totolElectricity;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getAccountDetailId() {
		return accountDetailId;
	}
	public void setAccountDetailId(Long accountDetailId) {
		this.accountDetailId = accountDetailId;
	}
	
}
