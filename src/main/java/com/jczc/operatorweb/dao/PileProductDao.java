package com.jczc.operatorweb.dao;

import com.jczc.operatorweb.entity.PileProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PileProductDao {
     public List<PileProduct> queryAllPileProducts();
}
