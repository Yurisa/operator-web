package com.jczc.operatorweb.util;

/**
 * @Author: LWJ
 * @Date: Created in 10:02 2017/11/28
 * @Description:
 */
public class DistanceUtil {
    private static final double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double GetDistanceKM(double srcLat, double srtLng, double destLat, double destLng) {
        double radLat1 = rad(srcLat);
        double radLat2 = rad(destLat);
        double a = radLat1 - radLat2;
        double b = rad(srtLng) - rad(destLng);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000) / 1000;
        return s;
    }
}
