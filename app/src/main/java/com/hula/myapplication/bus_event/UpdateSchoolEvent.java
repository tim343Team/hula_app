package com.hula.myapplication.bus_event;

public class UpdateSchoolEvent {
    private String school;

    public UpdateSchoolEvent(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
