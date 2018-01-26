package com.jczc.operatorweb.entity;

import java.util.Date;

public class PileGroup {
    private Integer id;
    private String name;
    private Integer operatorId;
    private Integer groupTypeId;
    private Byte chargingType;
    private Integer chargingPriceId;
    private Integer buildingsId;
    private Date createTime;
    private Integer creatorId;
    private Byte status;
    private Integer positionId;

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

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(Integer groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    public Byte getChargingType() {
        return chargingType;
    }

    public void setChargingType(Byte chargingType) {
        this.chargingType = chargingType;
    }

    public Integer getChargingPriceId() {
        return chargingPriceId;
    }

    public void setChargingPriceId(Integer chargingPriceId) {
        this.chargingPriceId = chargingPriceId;
    }

    public Integer getBuildingsId() {
        return buildingsId;
    }

    public void setBuildingsId(Integer buildingsId) {
        this.buildingsId = buildingsId;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}
