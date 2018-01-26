package com.jczc.operatorweb.model;

import java.util.Date;

public class ChargingPriceInfo {
    private Integer id;
    private String name;
    private String description;
    private Date createTime;
    private Integer creatorId;
    private Integer operatorId;
    private byte status;
    private double uniPrice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public double getUniPrice() {
		return uniPrice;
	}
	public void setUniPrice(double uniPrice) {
		this.uniPrice = uniPrice;
	}

    
}
