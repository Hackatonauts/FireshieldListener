package hackatonauts.fireshield.listener.helpers;

import java.util.Calendar;

public class Constants {

    public static String fireEvents = "https://eonet.sci.gsfc.nasa.gov/api/v2.1/categories/8";
    public static String filter = "?";
    public static String and = "&";
    public static String slash = "/";
    public static String daysParameter = "days=";
    public static String statusClosedParameter = "status=closed";
    public static String baseUrl = "http://192.168.137.1:8080/";
    public static String endpointName = "fire";
    public static String close = "close";
    public static String csvGlobal = "https://firms.modaps.eosdis.nasa.gov/active_fire/c6/text/MODIS_C6_Global_7d.csv";
    public static int sleepTime =2000;
    public static int timeIncreasingValue =12;
    public static int timeIncreasingUnit = Calendar.HOUR;
    public static int confidence = 100;
}
