package com.hula.myapplication.dao.home;

import java.util.List;

public class GroupDao implements Anthing{
    private String title;
    private String section_type;
    private List<GroupItemDao> groups;


    public List<GroupItemDao> getGroups() {
        return groups;
    }

    public String getSection_type() {
        return section_type;
    }

    public String getTitle() {
        return title;
    }
}
