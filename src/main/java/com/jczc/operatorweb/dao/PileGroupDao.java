package com.jczc.operatorweb.dao;

import com.jczc.operatorweb.entity.PileGroup;
import com.jczc.operatorweb.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PileGroupDao {
    public Integer save(PileGroup pileGroup);
    public PileGroupMessage queryPileGroupMessageById(Integer groupId);
    public List<GroupResource> queryOperatorAllGroupResource(Integer operatorId);
    public List<PileGroupInfo> queryPileGroupByStationId(@Param("operatorId")Integer operatorId,@Param("stationId") Integer stationId);
    public List<PileGroupInfo> queryPileGroupByZoneId(@Param("operatorId")Integer operatorId,@Param("zoneId") Integer zoneId);
    public List<PileGroupInfo> queryPileGroupByCityId(@Param("operatorId")Integer operatorId,@Param("cityId") Integer cityId);
    public List<PileGroupInfo> queryPileGroupByProvinceId(@Param("operatorId")Integer operatorId,@Param("provinceId") Integer provinceId);
    public GroupStationMessage queryGroupStationMessageByStationId(@Param("operatorId")Integer operatorId,@Param("stationId") Integer stationId);
    public GroupZoneMessage queryGroupZoneMessageByZoneId(@Param("operatorId")Integer operatorId,@Param("zoneId") Integer zoneId);
    public GroupCityMessage queryGroupCityMessageByCityId(@Param("operatorId")Integer operatorId,@Param("cityId") Integer cityId);
    public GroupProvinceMessage queryGroupProvinceMessageByProvinceId(@Param("operatorId")Integer operatorId,@Param("provinceId") Integer provinceId);
    public List<PileGroupInfo> queryGroupByRequirement(@Param("operatorId")Integer operatorId,@Param("groupRequireMent") GroupRequireMent groupRequireMent);
    public GroupAndPositon queryByGroupId(Integer groupId);
    public Integer updateByGroupId(PileGroup pileGroup);
}
