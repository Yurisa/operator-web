package com.jczc.operatorweb.entity;

import java.util.Date;

/**
 * Created by LWJ on 2017/10/22.
 */
public class ElectricVehicle {
    private int id;
    private String plateNo;
    private String brand;
    private String model;
    private int chargingUserId;
    private Date createTime;
    private byte status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getChargingUserId() {
        return chargingUserId;
    }

    public void setChargingUserId(int chargingUserId) {
        this.chargingUserId = chargingUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
