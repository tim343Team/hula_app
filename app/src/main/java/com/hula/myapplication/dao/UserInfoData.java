package com.hula.myapplication.dao;

import java.io.Serializable;
import java.util.List;

public class UserInfoData implements Serializable {
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

    public void setWishList(List<Object> wishList) {
        this.wishList = wishList;
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

    public void setMySchools(List<Object> mySchools) {
        this.mySchools = mySchools;
    }

    public void setSchoolIsPublic(boolean schoolIsPublic) {
        this.schoolIsPublic = schoolIsPublic;
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

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public void setWorkIsPublic(boolean workIsPublic) {
        this.workIsPublic = workIsPublic;
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
}
