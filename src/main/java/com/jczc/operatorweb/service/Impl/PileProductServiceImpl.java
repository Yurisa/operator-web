package com.jczc.operatorweb.service.Impl;

import com.jczc.operatorweb.dao.PileProductDao;
import com.jczc.operatorweb.entity.PileProduct;
import com.jczc.operatorweb.service.PileProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PileProductServiceImpl implements PileProductService {
    @Autowired
    PileProductDao pileProductDao;

    @Override
    public List<PileProduct> getAllPileProducts() {
        return pileProductDao.queryAllPileProducts();
    }
}
