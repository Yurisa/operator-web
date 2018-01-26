package com.jczc.operatorweb.model;

import java.util.Date;

public class ChargingStatistic {
    private Integer chargingId;
    private String pileIdentification;
    private String pileName;
    private Integer garageId;
    private String garageName;
    private String parkPotNo;
    private Integer chargingUserId;
    private String chargingUserName;
    private String vehicleNo;
    private Date chargingBeginTime;
    private Date chargingEndTime;
    private double totalChargingHours;
    private double totalChargingElectricity;
    private double totalChargingPrice;
    private Integer status;

    public Integer getChargingId() {
        return chargingId;
    }

    public void setChargingId(Integer chargingId) {
        this.chargingId = chargingId;
    }

    public String getPileIdentification() {
        return pileIdentification;
    }

    public void setPileIdentification(String pileIdentification) {
        this.pileIdentification = pileIdentification;
    }

    public String getPileName() {
        return pileName;
    }

    public void setPileName(String pileName) {
        this.pileName = pileName;
    }

    public Integer getGarageId() {
        return garageId;
    }

    public void setGarageId(Integer garageId) {
        this.garageId = garageId;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public String getParkPotNo() {
        return parkPotNo;
    }

    public void setParkPotNo(String parkPotNo) {
        this.parkPotNo = parkPotNo;
    }

    public Integer getChargingUserId() {
        return chargingUserId;
    }

    public void setChargingUserId(Integer chargingUserId) {
        this.chargingUserId = chargingUserId;
    }

    public String getChargingUserName() {
        return chargingUserName;
    }

    public void setChargingUserName(String chargingUserName) {
        this.chargingUserName = chargingUserName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Date getChargingBeginTime() {
        return chargingBeginTime;
    }

    public void setChargingBeginTime(Date chargingBeginTime) {
        this.chargingBeginTime = chargingBeginTime;
    }

    public Date getChargingEndTime() {
        return chargingEndTime;
    }

    public void setChargingEndTime(Date chargingEndTime) {
        this.chargingEndTime = chargingEndTime;
    }

    public double getTotalChargingHours() {
        return totalChargingHours;
    }

    public void setTotalChargingHours(double totalChargingHours) {
        this.totalChargingHours = totalChargingHours;
    }

    public double getTotalChargingElectricity() {
        return totalChargingElectricity;
    }

    public void setTotalChargingElectricity(double totalChargingElectricity) {
        this.totalChargingElectricity = totalChargingElectricity;
    }

    public double getTotalChargingPrice() {
        return totalChargingPrice;
    }

    public void setTotalChargingPrice(double totalChargingPrice) {
        this.totalChargingPrice = totalChargingPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
