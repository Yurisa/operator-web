package com.jczc.operatorweb.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jczc.operatorweb.model.ChargedListInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargedListDaoTest {
	@Autowired
	private ChargedListDao chargedListDao;
	@Test
	public void testUnstart(){
		ChargedListInfo obj=chargedListDao.getUnstart("3000000001");
		System.out.println(obj);
	}
}
