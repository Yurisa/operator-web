package com.jczc.operatorweb.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.model.AccountDetail;
import com.jczc.operatorweb.model.ChargingUser;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargingUserDaoTest {
	@Autowired
	ChargingUserDao userDao;
//	@Test
//	public void testGetUserByToken(){
//		ChargingUser u=userDao.getUserByAccessToken("f19995a8b5814bcbad3147333d29817a");
//		System.out.println(JSON.toJSON(u));
//	}
	@Test
	public void testCreateAccountDetail(){
		AccountDetail detail=new AccountDetail();
		detail.setAmount(0.01d);
		detail.setChangeTime(new Date());
		detail.setRemark("充电费用");
		detail.setPayStatus(1);
		detail.setType(2);
		detail.setAccountId(46);
		Long detailId=userDao.createUserAccountDetail(detail);
		System.out.println("new detail id:"+detail.getId());
	}
}
