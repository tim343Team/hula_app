package com.hula.myapplication.bus_event;

import com.hula.myapplication.dao.SchoolDao;

public class UpdateSchoolEvent {
    private SchoolDao school;

    public UpdateSchoolEvent(SchoolDao school) {
        this.school = school;
    }

    public SchoolDao getSchool() {
        return school;
    }

    public void setSchool(SchoolDao school) {
        this.school = school;
    }
}
