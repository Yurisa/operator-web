package com.jczc.operatorweb.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.model.ChargingFeeInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargingAccountServiceTest {
	@Autowired
	ChargingAccountService chargingAccountService;
	@Test
	public void getChargingFeeListByUserIdTest(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date start;
		try {
			start = sdf.parse("2017-10-1");
			Date end=sdf.parse("2018-1-4");
			PageInfo<ChargingFeeInfo> page=chargingAccountService.getChargingFeeListByUserId(38, start, end, 1, 10);	
			System.out.println(JSON.toJSONString(page));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}
