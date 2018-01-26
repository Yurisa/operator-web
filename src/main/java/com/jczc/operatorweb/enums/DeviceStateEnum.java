package com.jczc.operatorweb.enums;


//0x01	设备正常
//0x02	网络异常
//0x03	设备无法访问
//0x04	绝缘故障
//0x05	漏电故障
//    0x06	电压异常故障
//    0x07	交流接触器故障
//0x08	直流接触器故障
//0x09	连接器故障
//0x0A	温度过高
//0x0B	防雷故障
//0x0C	通讯故障

public enum DeviceStateEnum {
	WELL            			(1, "设备正常"),
    NET_ERROR         			(2, "网络异常"),
    ACCESS_ERROR           	 	(3, "设备无法访问"),
    INSULATION_ERROR			(4, "绝缘故障"),
    LEAKAGE_ERROR				(5, "漏电故障"),
    VOLTAGE_ERROR				(6, "电压异常故障"),
    AC_CONTACTOR_ERROR			(7, "交流接触器故障"),
    DC_CONTACTOR_ERROR			(8, "直流接触器故障"),
    CONNECTOR_ERROR				(9, "连接器故障"),
    TEMPERATURE_ERROR			(10, "温度过高"),
    LIGHTING_PROTECTION_ERROR	(11, "防雷故障"),
    COMMUNICATION_ERROR			(12, "通讯故障");
    /**
     * 类型值
     */
    public int code;
    /**
     * 描述
     */
    public String describe;

    DeviceStateEnum(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    
    public static DeviceStateEnum getByCode(int code){
    	for(DeviceStateEnum state:values()){
    		if(state.code==code)
    			return state;
    	}
    	return null;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
