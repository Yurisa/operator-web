package com.jczc.operatorweb.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.entity.Pile;
import com.jczc.operatorweb.model.PileInfo;

public interface PileService {
   public Integer addPile (Pile pile);
   public Pile getPileById(Integer pileId);
   public Integer updatePile(Pile pile);
   public PageInfo<PileInfo> getOperatorAllPiles(Integer operatorId, int pageNum, int pageSize);
   public PageInfo<PileInfo> getPileByBuildId(Integer stationId, int pageNum, int pageSize);
   public PageInfo<PileInfo> getPileByGroupId(Integer groupId, int paheNum, int pageSize);
   public Integer removePileById(Integer pileId);
   public List<PileInfo> getPilesForDistance(double lat1, double lng1, double distance);
   public PileInfo getPileByNo(String pileNo);
   public List<String> getAllPileIdentification();
   
}
