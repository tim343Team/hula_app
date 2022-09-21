package com.hula.myapplication.request;

public class CreateProfileParameter {
    private String user_id;
    private String name;
    private int id;

    public CreateProfileParameter(String user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }

    public CreateProfileParameter(String user_id, int id) {
        this.user_id = user_id;
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
