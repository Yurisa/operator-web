package com.jczc.operatorweb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccountDetail {
	private Long id;
	private double amount;
	private Date changeTime;
	private String remark;
	private Integer type;
	private Integer payStatus;
	private Long accountId;
	private List<ChargedFeeList> chargedFeeList= new ArrayList<ChargedFeeList>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public List<ChargedFeeList> getChargedFeeList() {
		return chargedFeeList;
	}
	public void setChargedFeeList(List<ChargedFeeList> chargedFeeList) {
		this.chargedFeeList = chargedFeeList;
	}
	
}
