package com.jczc.operatorweb.util;

import java.util.UUID;

import com.jczc.operatorweb.model.ChargingUser;


public class EncryptUtil {
	private static String getUUID() {  
        return UUID.randomUUID().toString().replace("-", "");  
    }  
	public static String createAccessToken(ChargingUser chargingUser){
		return getUUID();
	}
	public static String encryptPassword(String pwd){
		return pwd;
	}
}
