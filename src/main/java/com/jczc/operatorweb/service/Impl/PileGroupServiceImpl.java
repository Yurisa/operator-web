package com.jczc.operatorweb.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.dao.PileGroupDao;
import com.jczc.operatorweb.entity.PileGroup;
import com.jczc.operatorweb.entity.Position;
import com.jczc.operatorweb.model.*;
import com.jczc.operatorweb.service.PileGroupService;
import com.jczc.operatorweb.service.PositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PileGroupServiceImpl implements PileGroupService {

    @Autowired
    PileGroupDao pileGroupDao;

    @Autowired
    PositionService positionService;

    @Override
    public PileGroupMessage getPileGroupMessageById(Integer groupId) {
        return pileGroupDao.queryPileGroupMessageById(groupId);
    }

    @Override
    public List<GroupResource> getOperatorAllGroupResource(Integer operatorId) {
        return pileGroupDao.queryOperatorAllGroupResource(operatorId);
    }

    @Override
    public PageInfo<PileGroupInfo> getPileGroupByStationId(Integer operatorId, Integer stationId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PileGroupInfo> pileGroupInfos = pileGroupDao.queryPileGroupByStationId(operatorId, stationId);
        return new PageInfo<>(pileGroupInfos);
    }

    @Override
    public PageInfo<PileGroupInfo> getPileGroupByZoneId(Integer operatorId, Integer zoneId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PileGroupInfo> pileGroupInfos = pileGroupDao.queryPileGroupByZoneId(operatorId, zoneId);
        return new PageInfo<>(pileGroupInfos);
    }

    @Override
    public PageInfo<PileGroupInfo> getPileGroupByCityId(Integer operatorId, Integer cityId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PileGroupInfo> pileGroupInfos = pileGroupDao.queryPileGroupByCityId(operatorId, cityId);
        return new PageInfo<>(pileGroupInfos);
    }

    @Override
    public PageInfo<PileGroupInfo> getPileGroupByProvinceId(Integer operatorId, Integer provinceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PileGroupInfo> pileGroupInfos = pileGroupDao.queryPileGroupByProvinceId(operatorId, provinceId);
        return new PageInfo<>(pileGroupInfos);
    }

    @Override
    public GroupStationMessage getGroupStationMessageByStationId(Integer operatorId, Integer stationId) {
        return pileGroupDao.queryGroupStationMessageByStationId(operatorId, stationId);
    }

    @Override
    public GroupZoneMessage getGroupZoneMessageByZoneId(Integer operatorId, Integer zoneId) {
        return pileGroupDao.queryGroupZoneMessageByZoneId(operatorId, zoneId);
    }

    @Override
    public GroupCityMessage getGroupCityMessageByCityId(Integer operatorId, Integer cityId) {
        return pileGroupDao.queryGroupCityMessageByCityId(operatorId, cityId);
    }

    @Override
    public GroupProvinceMessage getGroupProvinceMessageByProvinceId(Integer operatorId, Integer provinceId) {
        return pileGroupDao.queryGroupProvinceMessageByProvinceId(operatorId, provinceId);
    }

    @Override
    public PageInfo<PileGroupInfo> getGroupByRequirement(Integer operatorId, GroupRequireMent groupRequireMent, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PileGroupInfo> pileGroupInfos = pileGroupDao.queryGroupByRequirement(operatorId, groupRequireMent);
        return new PageInfo<>(pileGroupInfos);
    }

    @Override
    public Map<String, Integer> addPileGroupAndPositon(GroupAndPositon groupAndPositon) {
        Map<String, Integer> resultMap = new HashMap<>();
        Position position = new Position();
        PileGroup pileGroup = new PileGroup();
        BeanUtils.copyProperties(groupAndPositon, pileGroup);
        BeanUtils.copyProperties(groupAndPositon, position);
        position.setCreateTime(new Date());
        pileGroup.setCreateTime(new Date());
        Integer posIncremId = positionService.addPosition(position);
        pileGroup.setPositionId(posIncremId);
        Integer groupIncreId = pileGroupDao.save(pileGroup);
        resultMap.put("pileGroupId", groupIncreId);
        resultMap.put("positonId", posIncremId);
        return resultMap;
    }

    @Override
    public GroupAndPositon getGroupById(Integer groupId) {
        return pileGroupDao.queryByGroupId(groupId);
    }

    @Override
    public Integer updatePileGroupAndPosition(GroupAndPositon groupAndPositon) {
        Position position = new Position();
        PileGroup pileGroup = new PileGroup();
        BeanUtils.copyProperties(groupAndPositon, pileGroup);
        BeanUtils.copyProperties(groupAndPositon, position);
        pileGroup.setId(groupAndPositon.getGroupId());
        position.setId(groupAndPositon.getPositionId());
        positionService.updatePosition(position);
        pileGroupDao.updateByGroupId(pileGroup);
        return 1;
    }


}
