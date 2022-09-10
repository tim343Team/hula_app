package com.hula.myapplication.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchSectionsDao {
    private String title;
    private List<Item> items;

    public String getTitle() {
        return title;
    }

    public List<Item> getItems() {
        return items;
    }

    public static class Item {
        private boolean approved;
        private String created_at;
        private int id;
        @SerializedName(value = "title", alternate = {"name"})
        private String title;
        private String pin_location;
        private String needs_user_location;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public int getId() {
            return id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public boolean isApproved() {
            return approved;
        }
    }


}
