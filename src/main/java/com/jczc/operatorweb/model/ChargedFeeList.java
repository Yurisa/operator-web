package com.jczc.operatorweb.model;

import java.util.Date;

/**
 * Created by LWJ on 2017/10/22.
 */
public class ChargedFeeList {
    private long id;
    private Double fee;
    private Date createTime;
    private double electricity;
	private Date feeTime;
    private Integer feeType;
    private String remark;
    private Integer status;
    private long accountDetailId;
    private long chargedListId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Date getFeeTime() {
        return feeTime;
    }

    public void setFeeTime(Date feeTime) {
        this.feeTime = feeTime;
    }
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double getElectricity() {
		return electricity;
	}

	public void setElectricity(double electricity) {
		this.electricity = electricity;
	}
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getAccountDetailId() {
        return accountDetailId;
    }

    public void setAccountDetailId(long accountDetailId) {
        this.accountDetailId = accountDetailId;
    }

    public long getChargedListId() {
        return chargedListId;
    }

    public void setChargedListId(long chargedListId) {
        this.chargedListId = chargedListId;
    }

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
}