package com.jczc.operatorweb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.model.ChargedListInfo;
import com.jczc.operatorweb.model.ChargingDataInfo;
import com.jczc.operatorweb.model.ChargingFeeInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargedListServiceTest {
	@Autowired
	private ChargedListService chargedListService;
	@Autowired
	private ChargingAccountService chargingAccountService;
	@Test
	public void getHistoryData(){
		ChargedListInfo info=chargedListService.getChargedList(34L);
		System.out.println("info:"+JSON.toJSONString(info));
		PageInfo<ChargingDataInfo> page=chargedListService.getHistoryChargingData(info, 1, 10);
		System.out.println("charging data:"+JSON.toJSONString(page.getList()));
	}
	@Test
	public void createChargingFeeTest(){
		ChargedListInfo info=chargedListService.getChargedList(59L);
		ChargingFeeInfo fee=chargingAccountService.createChargingFee(info);
		System.out.println("charging fee:"+JSON.toJSONString(fee));
	}
}
