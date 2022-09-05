package com.hula.myapplication.dao.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubEventsItem {
    private int trending;
    @SerializedName("joined_matching_pool_count")
    private int joinedMatchingPoolCount;
    @SerializedName("page_pange")
    private String pageRange;
    private String city;
    @SerializedName("sub_category")
    private List<SubCategoryItem> subCategory;
    private String description;
    private int searchCount;
    private boolean liked;
    private boolean approved;
    private boolean expired;
    private boolean joinTheMatchingPool;
    private String startingPrice;
    private int price;
    @SerializedName("pin_location")
    private String pinLocation;
    private int id;
    private Object firstName;
    @SerializedName("image_link")
    private String imageLink;
    @SerializedName("ticket_price")
    private String ticketPrice;
    private Object profile;
    private String image1;
    @SerializedName("seats_available")
    private boolean seatsAvailable;
    @SerializedName("event_url")
    private String eventUrl;
    private String ending;
    @SerializedName("creating_to_recommend_event")
    private boolean creatingToRecommendEvent;
    private Object userId;
    @SerializedName("interested_count")
    private int interestedCount;
    private String phone;
    private String name;
    @SerializedName("creating_to_find_buddy")
    private boolean creatingToFindBuddy;
    private String location;
    private String starting;

    public int getTrending() {
        return trending;
    }

    public int getJoinedMatchingPoolCount() {
        return joinedMatchingPoolCount;
    }

    public String getPageRange() {
        return pageRange;
    }

    public String getCity() {
        return city;
    }

    public List<SubCategoryItem> getSubCategory() {
        return subCategory;
    }

    public String getDescription() {
        return description;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public boolean isLiked() {
        return liked;
    }

    public boolean isApproved() {
        return approved;
    }

    public boolean isExpired() {
        return expired;
    }

    public boolean isJoinTheMatchingPool() {
        return joinTheMatchingPool;
    }

    public String getStartingPrice() {
        return startingPrice;
    }

    public int getPrice() {
        return price;
    }

    public String getPinLocation() {
        return pinLocation;
    }

    public int getId() {
        return id;
    }

    public Object getFirstName() {
        return firstName;
    }

    public String getImageLink() {
        return imageLink;
    }


    public String getTicketPrice() {
        return ticketPrice;
    }

    public Object getProfile() {
        return profile;
    }

    public String getImage1() {
        return image1;
    }

    public boolean isSeatsAvailable() {
        return seatsAvailable;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public String getEnding() {
        return ending;
    }

    public boolean isCreatingToRecommendEvent() {
        return creatingToRecommendEvent;
    }

    public Object getUserId() {
        return userId;
    }

    public int getInterestedCount() {
        return interestedCount;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public boolean isCreatingToFindBuddy() {
        return creatingToFindBuddy;
    }

    public String getLocation() {
        return location;
    }

    public String getStarting() {
        return starting;
    }
}