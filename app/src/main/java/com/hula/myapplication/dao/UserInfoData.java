package com.hula.myapplication.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfoData implements Serializable {
    private Object isEventVisited;
    private int distance;
    private boolean botInteraction;
    private String city;
    private String about;
    private String createdAt;
    private List<WishListDao> wish_list;
    private String linkedAccounts;
    private String school;
    private String pronoun;
    private Object pinLocation;
    private List<Object> defaultProfileTags;
    private int id;
    private List<SchoolDao> my_schools;
    private boolean school_is_public;
    private boolean emailVerified;
    private String work;
    private String profile;
    private BrowsingCity browsingCity;
    private List<Object> interestedEvents;
    private boolean team;
    private String display_name;
    private String drink;
    private Object isBlockedByMe;
    private List<Object> tags;
    private List<Object> myProfileTags;
    private Object isJoinMatchingPool;
    private boolean work_is_public;
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
    private List<ProfileTagDao> my_profile_tags = new ArrayList<>();
    private int school_id;

    public Object getIsEventVisited() {
        return isEventVisited;
    }

    public int getDistance() {
        return distance;
    }

    public boolean isBotInteraction() {
        return botInteraction;
    }

    public String getCity() {
        return city;
    }

    public String getAbout() {
        return about;
    }

    public String getCreatedAt() {
        return createdAt;
    }


    public String getLinkedAccounts() {
        return linkedAccounts;
    }

    public String getSchool() {
        return school;
    }

    public String getPronoun() {
        return pronoun;
    }

    public Object getPinLocation() {
        return pinLocation;
    }

    public List<Object> getDefaultProfileTags() {
        return defaultProfileTags;
    }

    public int getId() {
        return id;
    }


    public boolean isEmailVerified() {
        return emailVerified;
    }

    public String getWork() {
        return work;
    }

    public String getProfile() {
        return profile;
    }

    public BrowsingCity getBrowsingCity() {
        return browsingCity;
    }

    public List<Object> getInterestedEvents() {
        return interestedEvents;
    }

    public boolean isTeam() {
        return team;
    }


    public String getDrink() {
        return drink;
    }

    public Object getIsBlockedByMe() {
        return isBlockedByMe;
    }

    public List<Object> getTags() {
        return tags;
    }

    public List<Object> getMyProfileTags() {
        return myProfileTags;
    }

    public Object getIsJoinMatchingPool() {
        return isJoinMatchingPool;
    }


    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public Object getHasBlockedMe() {
        return hasBlockedMe;
    }

    public List<Object> getMyPhotos() {
        return myPhotos;
    }

    public Object getLocation() {
        return location;
    }

    public Object getInterests() {
        return interests;
    }

    public User getUser() {
        return user;
    }

    public boolean isDrinkIsPublic() {
        return drink_is_public;
    }

    public int getAge() {
        return age;
    }

    public void setIsEventVisited(Object isEventVisited) {
        this.isEventVisited = isEventVisited;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setBotInteraction(boolean botInteraction) {
        this.botInteraction = botInteraction;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setLinkedAccounts(String linkedAccounts) {
        this.linkedAccounts = linkedAccounts;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public void setPinLocation(Object pinLocation) {
        this.pinLocation = pinLocation;
    }

    public void setDefaultProfileTags(List<Object> defaultProfileTags) {
        this.defaultProfileTags = defaultProfileTags;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setBrowsingCity(BrowsingCity browsingCity) {
        this.browsingCity = browsingCity;
    }

    public void setInterestedEvents(List<Object> interestedEvents) {
        this.interestedEvents = interestedEvents;
    }

    public void setTeam(boolean team) {
        this.team = team;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setIsBlockedByMe(Object isBlockedByMe) {
        this.isBlockedByMe = isBlockedByMe;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public void setMyProfileTags(List<Object> myProfileTags) {
        this.myProfileTags = myProfileTags;
    }

    public void setIsJoinMatchingPool(Object isJoinMatchingPool) {
        this.isJoinMatchingPool = isJoinMatchingPool;
    }


    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setHasBlockedMe(Object hasBlockedMe) {
        this.hasBlockedMe = hasBlockedMe;
    }

    public void setMyPhotos(List<Object> myPhotos) {
        this.myPhotos = myPhotos;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public void setInterests(Object interests) {
        this.interests = interests;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDrink_is_public(boolean drink_is_public) {
        this.drink_is_public = drink_is_public;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDrink_is_public() {
        return drink_is_public;
    }

    public List<ProfileTagDao> getMy_profile_tags() {
        if (my_profile_tags == null) {
            my_profile_tags = new ArrayList<>();
        }
        return my_profile_tags;
    }

    public void setMy_profile_tags(List<ProfileTagDao> my_profile_tags) {
        this.my_profile_tags = my_profile_tags;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public List<WishListDao> getWish_list() {
        return wish_list;
    }

    public void setWish_list(List<WishListDao> wish_list) {
        this.wish_list = wish_list;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public List<SchoolDao> getMy_schools() {
        if (my_schools == null) {
            my_schools = new ArrayList<>();
        }
        return my_schools;
    }

    public void setMy_schools(List<SchoolDao> my_schools) {
        this.my_schools = my_schools;
    }

    public boolean isSchool_is_public() {
        return school_is_public;
    }

    public void setSchool_is_public(boolean school_is_public) {
        this.school_is_public = school_is_public;
    }

    public boolean isWork_is_public() {
        return work_is_public;
    }

    public void setWork_is_public(boolean work_is_public) {
        this.work_is_public = work_is_public;
    }
}
