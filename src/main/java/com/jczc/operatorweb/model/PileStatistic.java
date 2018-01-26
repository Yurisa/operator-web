package com.jczc.operatorweb.model;

public class PileStatistic {
    private Integer pileId;
    private String pileName;
    private Integer garageId;
    private String garageName;
    private String parkPotNo;
    private Integer totalChargingNum;
    private double totalChargingHours;
    private double totalChargingElectricity;
    private double 	totalChargingPrice;

    public Integer getPileId() {
        return pileId;
    }

    public void setPileId(Integer pileId) {
        this.pileId = pileId;
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

    public Integer getTotalChargingNum() {
        return totalChargingNum;
    }

    public void setTotalChargingNum(Integer totalChargingNum) {
        this.totalChargingNum = totalChargingNum;
    }
}
