package com.jczc.operatorweb.entity;

import io.swagger.models.auth.In;

public class Pile {
    private Integer id;
    private String identification;
    private Integer productId;
    private String managedBy;
    private Integer garageId;
    private String parkingPotNo;
    private Integer groupId;
    private Integer chargingPriceId;
    private Double gpsLat;
    private Double gpsLng;
    private Integer status;
    private Integer type;
    private String deviceStatus;
    private String chargingStatus;

    public Pile() {
    }

    public Pile(String identification, Integer productId, Integer garageId, String parkingPotNo, Integer groupId, Integer chargingPriceId, Double gpsLat, Double gpsLng, Integer status, Integer type, String deviceStatus, String chargingStatus) {
        this.identification = identification;
        this.productId = productId;
        this.garageId = garageId;
        this.parkingPotNo = parkingPotNo;
        this.groupId = groupId;
        this.chargingPriceId = chargingPriceId;
        this.gpsLat = gpsLat;
        this.gpsLng = gpsLng;
        this.status = status;
        this.type = type;
        this.deviceStatus = deviceStatus;
        this.chargingStatus = chargingStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(String managedBy) {
        this.managedBy = managedBy;
    }

    public Integer getGarageId() {
        return garageId;
    }

    public void setGarageId(Integer garageId) {
        this.garageId = garageId;
    }

    public String getParkingPotNo() {
        return parkingPotNo;
    }

    public void setParkingPotNo(String parkingPotNo) {
        this.parkingPotNo = parkingPotNo;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getChargingPriceId() {
        return chargingPriceId;
    }

    public void setChargingPriceId(Integer chargingPriceId) {
        this.chargingPriceId = chargingPriceId;
    }

    public Double getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(Double gpsLat) {
        this.gpsLat = gpsLat;
    }

    public Double getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(Double gpsLng) {
        this.gpsLng = gpsLng;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getChargingStatus() {
        return chargingStatus;
    }

    public void setChargingStatus(String chargingStatus) {
        this.chargingStatus = chargingStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
