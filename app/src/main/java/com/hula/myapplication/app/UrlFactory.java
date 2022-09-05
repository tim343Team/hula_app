package com.hula.myapplication.app;

import com.hula.myapplication.config.AppConfig;

public class UrlFactory {
    public static final String host = AppConfig.BASE_URL;


    public static String getSubCategories() {
        return host + "/user/get_sub_categories";
    }

    public static String getProfile() {
        return host + "/user/get_profile";
    }

    public static String getAllHomeEventsV3() {
        return host + "/events/all_home_events_v3";
    }

    public static String findBuddyV2() {
        return host + "/events/find_buddy_v2";
    }
}
