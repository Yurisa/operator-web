package com.jczc.operatorweb.dao;

import com.jczc.operatorweb.entity.Pile;
import com.jczc.operatorweb.model.PileInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PileDao {
    public Integer save(Pile pile);
    public Pile queryById(Integer pileId);
    public Integer updateById(Pile pile);
    public Integer delete(Integer pileId);
    public List<PileInfo> queryOperatorAllPiles(@Param("operatorId") Integer operatorId);
    public List<PileInfo> queryPileByBuildId(Integer stationId);
    public List<PileInfo> queryPileByGroupId(Integer groupId);
	public Integer updateByNo(Pile pile);
	public PileInfo getPileByNo(String pileNo);
	public List<String> getAllPileIdentification ();
}
