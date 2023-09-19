package com.swa.escape.utils;

import static java.lang.Math.*;

// 참고 : https://en.wikipedia.org/wiki/Haversine_formula
public class Calcul {
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        lat1 = toRadians(lat1);
        lon1 = toRadians(lon1);
        lat2 = toRadians(lat2);
        lon2 = toRadians(lon2);
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = sin(dlat / 2)*sin(dlat / 2) + cos(lat1) * cos(lat2) * sin(dlon / 2) * sin(dlon / 2);
        double c = 2 * asin(sqrt(a));
        double r = 6371;
        return c * r;
    }
//    public static double haversine()
//    public static double haversine()
//    public static double haversine()
//    public static double haversine()
}
