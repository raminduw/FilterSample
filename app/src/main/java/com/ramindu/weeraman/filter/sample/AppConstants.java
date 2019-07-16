package com.ramindu.weeraman.filter.sample;

public class AppConstants {

    //Default values as below, User can change any constant for testing purpose
    public static String BASE_URL = "https://testapi-b0e59.firebaseio.com/";

    public static int FILTER_MIN_AGE = 18;
    public static int FILTER_MAX_AGE = 95;

    public static int FILTER_MIN_HEIGHT = 135;
    public static int FILTER_MAX_HEIGHT = 210;

    public static int FILTER_MIN_DISTANCE = 30;
    public static int FILTER_MAX_DISTANCE = 300;

    public static int FILTER_MIN_COMPATIBILITY_SCORE = 0;
    public static int FILTER_MAX_COMPATIBILITY_SCORE = 99;

    public AppConstants(String baseUrl){
        BASE_URL = baseUrl;
    }
    public static void setFilterMinAge(int filterMinAge) {
        FILTER_MIN_AGE = filterMinAge;
    }
    public static void setFilterMaxAge(int filterMaxAge) {
        FILTER_MAX_AGE = filterMaxAge;
    }
    public static void setFilterMinHeight(int filterMinHeight) {
        FILTER_MIN_HEIGHT = filterMinHeight;
    }
    public static void setFilterMaxHeight(int filterMaxHeight) {
        FILTER_MAX_HEIGHT = filterMaxHeight;
    }
    public static void setFilterMinDistance(int filterMinDistance) {
        FILTER_MIN_DISTANCE = filterMinDistance;
    }
    public static void setFilterMaxDistance(int filterMaxDistance) {
        FILTER_MAX_DISTANCE = filterMaxDistance;
    }
    public static void setFilterMinCompatibilityScore(int filterMinCompatibilityScore) {
        FILTER_MIN_COMPATIBILITY_SCORE = filterMinCompatibilityScore;
    }
    public static void setFilterMaxCompatibilityScore(int filterMaxCompatibilityScore) {
        FILTER_MAX_COMPATIBILITY_SCORE = filterMaxCompatibilityScore;
    }
}
