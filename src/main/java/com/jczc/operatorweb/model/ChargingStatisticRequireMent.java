package com.jczc.operatorweb.model;

import java.util.Date;

public class ChargingStatisticRequireMent {
    private Integer zoneId = -1;
    private Integer cityId = -1;
    private Integer provinceId = -1;
    private String pileIdentification;
    private String chargingUserName;
    private Integer status;
    private Date beginTime;
    private Date endTime;

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getPileIdentification() {
        return pileIdentification;
    }

    public void setPileIdentification(String pileIdentification) {
        this.pileIdentification = pileIdentification;
    }

    public String getChargingUserName() {
        return chargingUserName;
    }

    public void setChargingUserName(String chargingUserName) {
        this.chargingUserName = chargingUserName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
