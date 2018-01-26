package com.jczc.operatorweb.enums;

public enum ChargingStateEnum {
//	0x01	车位空闲
//	0x02	车牌识别成功
//	0x03	车辆充电枪刚插好
//	0x04	正在充电
//	0x05	车充满
//	    0x06	枪断开连接
//	    0x07	车离开，车位空闲
	IDLE			(1,"空闲"),
	PARK_START		(2,"请求充电"),
	CHARGING_BEGIN	(3,"开始充电"),
	CHARGING		(4,"正在充电"),
	CHARGING_FULL	(5,"电已充满"),
	CHARGING_END	(6,"充电结束"),
	PARK_END		(7,"离开车位");
	
	private int code;
	private String name;
	
	ChargingStateEnum(int code,String name){
		this.code=code;
		this.name=name;
	}
	public static ChargingStateEnum getByCode(int code){
		for(ChargingStateEnum e:values()){
			if(e.getCode()==code)
				return e;
		}
		return null;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
