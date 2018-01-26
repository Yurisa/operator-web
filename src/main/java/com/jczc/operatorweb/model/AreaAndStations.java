package com.jczc.operatorweb.model;

import com.jczc.operatorweb.entity.PileStation;

import java.util.List;

public class AreaAndStations {
    private Integer zoneId;
    private String zoneName;
    private Integer cityId;
    private String cityName;
    private Integer provinceId;
    private String provinceName;
    private List<PileStation>  pileStations;

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<PileStation> getPileStations() {
        return pileStations;
    }

    public void setPileStations(List<PileStation> pileStations) {
        this.pileStations = pileStations;
    }
}
