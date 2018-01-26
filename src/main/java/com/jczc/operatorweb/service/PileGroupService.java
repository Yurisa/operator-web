package com.jczc.operatorweb.service;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.entity.PileGroup;
import com.jczc.operatorweb.entity.Position;
import com.jczc.operatorweb.model.*;

import java.util.List;
import java.util.Map;

public interface PileGroupService {
    public PileGroupMessage getPileGroupMessageById(Integer groupId);
    public List<GroupResource> getOperatorAllGroupResource(Integer operatorId);
    public PageInfo<PileGroupInfo> getPileGroupByStationId(Integer operatorId, Integer stationId, int pageNum, int pageSize);
    public PageInfo<PileGroupInfo> getPileGroupByZoneId(Integer operatorId, Integer zoneId, int pageNum, int pageSize);
    public PageInfo<PileGroupInfo> getPileGroupByCityId(Integer operatorId, Integer cityId, int pageNum, int pageSize);
    public PageInfo<PileGroupInfo> getPileGroupByProvinceId(Integer operatorId, Integer provinceId, int pageNum, int pageSize);
    public GroupStationMessage getGroupStationMessageByStationId(Integer operatorId, Integer stationId);
    public GroupZoneMessage getGroupZoneMessageByZoneId(Integer operatorId, Integer zoneId);
    public GroupCityMessage getGroupCityMessageByCityId(Integer operatorId, Integer cityId);
    public GroupProvinceMessage getGroupProvinceMessageByProvinceId(Integer operatorId, Integer provinceId);
    public PageInfo<PileGroupInfo> getGroupByRequirement(Integer operatorId, GroupRequireMent groupRequireMent, int pageNum, int pageSize);
    public Map<String, Integer> addPileGroupAndPositon(GroupAndPositon groupAndPositon);
    public GroupAndPositon getGroupById(Integer groupId);
    public Integer updatePileGroupAndPosition(GroupAndPositon groupAndPositon);
}
