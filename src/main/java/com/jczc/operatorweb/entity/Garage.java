package com.jczc.operatorweb.entity;

import java.util.Date;

public class Garage {
    private Integer id;
    private String name;
    private String description;
    private Date createTime;
    private Integer createorId;
    private Integer buildingsId;
    private Double  GpsLat;
    private Double GpsIng;
    private Integer OrgId;

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

    public Integer getCreateorId() {
        return createorId;
    }

    public void setCreateorId(Integer createorId) {
        this.createorId = createorId;
    }

    public Integer getBuildingsId() {
        return buildingsId;
    }

    public void setBuildingsId(Integer buildingsId) {
        this.buildingsId = buildingsId;
    }

    public Double getGpsLat() {
        return GpsLat;
    }

    public void setGpsLat(Double gpsLat) {
        GpsLat = gpsLat;
    }

    public Double getGpsIng() {
        return GpsIng;
    }

    public void setGpsIng(Double gpsIng) {
        GpsIng = gpsIng;
    }

    public Integer getOrgId() {
        return OrgId;
    }

    public void setOrgId(Integer orgId) {
        OrgId = orgId;
    }
}
