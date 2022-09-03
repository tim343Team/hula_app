package com.hula.myapplication.app;

import com.hula.myapplication.config.AppConfig;

public class UrlFactory {
    public static final String host = AppConfig.BASE_URL;


    public static String getSubCategories() {
        return host + "/user/get_sub_categories";
    }
}
