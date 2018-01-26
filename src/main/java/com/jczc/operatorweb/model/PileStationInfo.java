package com.jczc.operatorweb.model;

import java.util.Date;

public class PileStationInfo {
    private Integer id;
    private String name;
    private String zoneName;
    private String cityName;
    private String provinceName;
    private String detailAddress;
    private Integer pileNum = 0;
    private Integer chargingNum = 0;
    private Integer brokenNum = 0;
    private double totalElectricity;
    private double totalPrice;
    private Integer status;
    private Date createTime;


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


    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Integer getPileNum() {
        return pileNum;
    }

    public void setPileNum(Integer pileNum) {
        this.pileNum = pileNum;
    }

    public Integer getChargingNum() {
        return chargingNum;
    }

    public void setChargingNum(Integer chargingNum) {
        this.chargingNum = chargingNum;
    }

    public Integer getBrokenNum() {
        return brokenNum;
    }

    public void setBrokenNum(Integer brokenNum) {
        this.brokenNum = brokenNum;
    }

    public double getTotalElectricity() {
        return totalElectricity;
    }

    public void setTotalElectricity(double totalElectricity) {
        this.totalElectricity = totalElectricity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
