package com.jczc.operatorweb.model;

public class GroupResource {
     private Integer groupTypeId;
     private String groupType;
     private Integer maxNum;
     private Integer allocNum;
     private Integer residueNum;

    public Integer getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(Integer groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getAllocNum() {
        return allocNum;
    }

    public void setAllocNum(Integer allocNum) {
        this.allocNum = allocNum;
    }

    public Integer getResidueNum() {
        return residueNum;
    }

    public void setResidueNum(Integer residueNum) {
        this.residueNum = residueNum;
    }
}
