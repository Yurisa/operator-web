package com.jczc.operatorweb.model;

public class StationAndElectrity {
    private Integer id;
    private String name;
    private Integer zoneId;
    private Integer cityId;
    private Integer provinceId;
    private Integer pileNum = 0;
    private Integer chargingTimes=0;//充电次数
    private Double  charginghours=0.0;//充电时长（小时）
    private Double electricity;//电量
    private Double chargingPrice;//电费

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

    public Integer getPileNum() {
        return pileNum;
    }

    public void setPileNum(Integer pileNum) {
        this.pileNum = pileNum;
    }

    public Integer getChargingTimes() {
        return chargingTimes;
    }

    public void setChargingTimes(Integer chargingTimes) {
        this.chargingTimes = chargingTimes;
    }

    public Double getCharginghours() {
        return charginghours;
    }

    public void setCharginghours(Double charginghours) {
        this.charginghours = charginghours;
    }

    public Double getElectricity() {
        return electricity;
    }

    public void setElectricity(Double electricity) {
        this.electricity = electricity;
    }

    public Double getChargingPrice() {
        return chargingPrice;
    }

    public void setChargingPrice(Double chargingPrice) {
        this.chargingPrice = chargingPrice;
    }

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
}
