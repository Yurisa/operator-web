package com.jczc.operatorweb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static Long YEAR2000=946656000000L;
	public static Date getFirtDate(){
		return new Date(YEAR2000);
	}
	public static Date parseDateStr(String dateStr){
    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	try {
			return sdf2.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			try {
				return sdf1.parse(dateStr);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				return null;
			}
		}
    }
}
