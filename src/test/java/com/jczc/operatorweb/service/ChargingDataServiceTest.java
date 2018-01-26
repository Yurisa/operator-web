package com.jczc.operatorweb.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jczc.operatorweb.model.ChargingDataInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargingDataServiceTest {
	@Autowired
	ChargingDataService service;
	@Test
	public void addChargingData(){
		//{"current":,"electricity":,"pileNo":"","power":,"receiveTime":,"voltage":}
		ChargingDataInfo d=new ChargingDataInfo();
		d.setCurrent(5.12);
		d.setPileNo("3000000001");
		d.setPower(101.0);
		d.setElectricity(92.16);
		d.setReceiveTime(new Date());
		d.setVoltage(271.44);
		service.addChargingData(d);
	}
}
