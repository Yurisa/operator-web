package com.jczc.operatorweb.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.dao.ChargingDataDao;
import com.jczc.operatorweb.dao.ChargingPriceDao;
import com.jczc.operatorweb.entity.ChargingPrice;
import com.jczc.operatorweb.model.ChargedListInfo;
import com.jczc.operatorweb.model.ChargingDataInfo;
import com.jczc.operatorweb.model.ChargingFeeInfo;
import com.jczc.operatorweb.model.ChargingPriceInfo;
import com.jczc.operatorweb.service.ChargingPriceService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChargingPriceImpl implements ChargingPriceService {

    @Autowired
    ChargingPriceDao chargingPriceDao;
    @Autowired
    ChargingDataDao chargingDataDao;

    @Override
    public PageInfo<ChargingPriceInfo> getAllChargingPrice(Integer operator, int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<ChargingPriceInfo> chargingPriceInfos = chargingPriceDao.queryAllChargingPrice(operator);
        return new PageInfo<>(chargingPriceInfos);
    }

    @Override
    public List<ChargingPriceInfo> getTotalChargingPrice(Integer operatorId) {
        return chargingPriceDao.queryAllChargingPrice(operatorId);
    }

    @Override
    public Integer addChargingPrice(ChargingPrice chargingprice) {
    	Date time = new Date(); 
    	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	//chargingprice.setCreateTime(Timestamp.valueOf(dateFormat.format( time )));
    	chargingprice.setCreateTime(time );
        return (Integer)chargingPriceDao.save(chargingprice);
    }
    
    @Override
    public Integer removeChargingPriceById(Integer chargingpriceId) {
        return chargingPriceDao.delete(chargingpriceId);
    }
    
    @Override
    public Integer updateChargingPrice(ChargingPrice chargingprice) {
        return chargingPriceDao.updateById(chargingprice);
    }

	@Override
	public ChargingPrice getPileChargingPrice(String chargingPileNo) {
		return chargingPriceDao.getPileChargingPrice(chargingPileNo);
	}

	
}
