package com.hula.myapplication.app;

import com.hula.myapplication.config.AppConfig;

public class UrlFactory {
    public static final String host = AppConfig.BASE_URL;


    public static String getSubCategories() {
        return host + "/user/get_sub_categories";
    }

    public static String updateProfile() {
        return host + "/user/update_profile";
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

    public static String login(){
        return host +"/user/login";
    }

    public static String getAllSections() {
        return host + "/events/get_all_sections";
    }

    //获取默认profile tag
    public static String getDefaultProfileTags() {
        return host + "/user/get_default_profile_tags";
    }

    //添加接口profile tag
    public static String createProfileTag() {
        return host + "/user/create_profile_tag";
    }

    //删除接口profile tag
    public static String deleteProfileTag() {
        return host + "/user/delete_profile_tag";
    }

    //school列表
    public static String getSchoolList() {
        return host + "/events/schools";
    }

    //添加school
    public static String addSchoolList() {
        return host + "/events/add_school";
    }

    //更改设置profile
    public static String updateProfile() {
        return host + "/user/update_profile";
    }
}
