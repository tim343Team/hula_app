package com.hula.myapplication.dao;

import java.util.List;

public class UserInfoData {
    private Object isEventVisited;
    private int distance;
    private boolean botInteraction;
    private String city;
    private String about;
    private String createdAt;
    private List<Object> wishList;
    private String linkedAccounts;
    private String school;
    private String pronoun;
    private Object pinLocation;
    private List<Object> defaultProfileTags;
    private int id;
    private List<Object> mySchools;
    private boolean schoolIsPublic;
    private boolean emailVerified;
    private String work;
    private String profile;
    private BrowsingCity browsingCity;
    private List<Object> interestedEvents;
    private boolean team;
    private String displayName;
    private String drink;
    private Object isBlockedByMe;
    private List<Object> tags;
    private List<Object> myProfileTags;
    private Object isJoinMatchingPool;
    private boolean workIsPublic;
    private boolean phoneVerified;
    private String phone;
    private String dob;
    private Object hasBlockedMe;
    private List<Object> myPhotos;
    private Object location;
    private Object interests;
    private User user;
    private boolean drink_is_public;
    private int age;

    public Object getIsEventVisited(){
        return isEventVisited;
    }

    public int getDistance(){
        return distance;
    }

    public boolean isBotInteraction(){
        return botInteraction;
    }

    public String getCity(){
        return city;
    }

    public String getAbout(){
        return about;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public List<Object> getWishList(){
        return wishList;
    }

    public String getLinkedAccounts(){
        return linkedAccounts;
    }

    public String getSchool(){
        return school;
    }

    public String getPronoun(){
        return pronoun;
    }

    public Object getPinLocation(){
        return pinLocation;
    }

    public List<Object> getDefaultProfileTags(){
        return defaultProfileTags;
    }

    public int getId(){
        return id;
    }

    public List<Object> getMySchools(){
        return mySchools;
    }

    public boolean isSchoolIsPublic(){
        return schoolIsPublic;
    }

    public boolean isEmailVerified(){
        return emailVerified;
    }

    public String getWork(){
        return work;
    }

    public String getProfile(){
        return profile;
    }

    public BrowsingCity getBrowsingCity(){
        return browsingCity;
    }

    public List<Object> getInterestedEvents(){
        return interestedEvents;
    }

    public boolean isTeam(){
        return team;
    }

    public String getDisplayName(){
        return displayName;
    }

    public String getDrink(){
        return drink;
    }

    public Object getIsBlockedByMe(){
        return isBlockedByMe;
    }

    public List<Object> getTags(){
        return tags;
    }

    public List<Object> getMyProfileTags(){
        return myProfileTags;
    }

    public Object getIsJoinMatchingPool(){
        return isJoinMatchingPool;
    }

    public boolean isWorkIsPublic(){
        return workIsPublic;
    }

    public boolean isPhoneVerified(){
        return phoneVerified;
    }

    public String getPhone(){
        return phone;
    }

    public String getDob(){
        return dob;
    }

    public Object getHasBlockedMe(){
        return hasBlockedMe;
    }

    public List<Object> getMyPhotos(){
        return myPhotos;
    }

    public Object getLocation(){
        return location;
    }

    public Object getInterests(){
        return interests;
    }

    public User getUser(){
        return user;
    }

    public boolean isDrinkIsPublic(){
        return drink_is_public;
    }

    public int getAge(){
        return age;
    }


    public UserInfoData copy(){
        return new UserInfoData();
    }
}
