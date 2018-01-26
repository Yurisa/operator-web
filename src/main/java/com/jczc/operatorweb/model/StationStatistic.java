package com.jczc.operatorweb.model;

public class StationStatistic {
    private Integer stationId;
    private String stationName;
    private int totalPileNum;
    private int totalChargingNum;
    private double totalChargingHours;
    private double totalChargingElectricity;
    private double totalChargingPrice;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getTotalPileNum() {
        return totalPileNum;
    }

    public void setTotalPileNum(int totalPileNum) {
        this.totalPileNum = totalPileNum;
    }

    public int getTotalChargingNum() {
        return totalChargingNum;
    }

    public void setTotalChargingNum(int totalChargingNum) {
        this.totalChargingNum = totalChargingNum;
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
}
