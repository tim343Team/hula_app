package com.hula.myapplication.dao;

public class PreferenceDao {
    private int id;

    private User user;

    private String first_name;

    private String email;

    private int age_range_start;

    private int age_range_end;

    private int distance;

    private String attend_events_with;

    private String created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge_range_start() {
        return age_range_start;
    }

    public void setAge_range_start(int age_range_start) {
        this.age_range_start = age_range_start;
    }

    public int getAge_range_end() {
        return age_range_end;
    }

    public void setAge_range_end(int age_range_end) {
        this.age_range_end = age_range_end;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getAttend_events_with() {
        return attend_events_with;
    }

    public void setAttend_events_with(String attend_events_with) {
        this.attend_events_with = attend_events_with;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
