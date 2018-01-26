package com.jczc.operatorweb.enums;

public enum PayMethodEnum {
	BALANCE			(1,"余额"),
	WX		(2,"微信"),
	ALI	(3,"支付宝"),
	CASH		(4,"现金");
	private int code;
	private String name;
	
	PayMethodEnum(int code,String name){
		this.code=code;
		this.name=name;
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
