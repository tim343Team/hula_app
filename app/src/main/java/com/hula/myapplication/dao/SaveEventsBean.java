package com.hula.myapplication.dao;

import com.hula.myapplication.dao.home.Anthing;

import java.util.List;
import java.util.Objects;

public class SaveEventsBean implements Anthing {
    public final String name;
    public final List<SubCategoriesDao> sub_categoryObj;

    public SaveEventsBean(String name, List<SubCategoriesDao> sub_categoryObj) {
        this.name = name;
        this.sub_categoryObj = sub_categoryObj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveEventsBean)) return false;
        SaveEventsBean that = (SaveEventsBean) o;
        return name.equals(that.name) && sub_categoryObj.equals(that.sub_categoryObj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sub_categoryObj);
    }
}
