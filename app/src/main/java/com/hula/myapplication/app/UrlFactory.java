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

    public static String getAllHomeEvents() {
        return host + "/events/all_home_events";
    }

    public static String findBuddyV2() {
        return host + "/events/find_buddy_v2";
    }


    public static String getLike() {
        return host + "/events/like_event";
    }

    public static String getUnLike() {
        return host + "/events/unlike_event";
    }

    public static String specificEventsList() {
        return host + "/events/specific_events_list";
    }

    public static String getAllCity() {
        return host + "/user/list_all_browsing_cities";
    }

    public static String eventSearch() {
        return host + "/events/event_search";
    }

    public static String getAllSections() {
        return host + "/events/get_all_sections";
    }

    //获取默认profile tag
    public static String getDefaultProfileTags() {
        return host + "/user/get_default_profile_tags";
    }
}
