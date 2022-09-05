package com.hula.myapplication.dao.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsItem{
	private int trending;
	@SerializedName("is_holiday")
	private boolean isHoliday;
	private int admin;
	@SerializedName("vendor_name")
	private String vendorName;
	@SerializedName("created_at")
	private String createdAt;
	@SerializedName("event_source")
	private String eventSource;
	@SerializedName("created_by")
	private int createdBy;
	@SerializedName("search_count")
	private int searchCount;
	private boolean liked;
	private List<SubEventsItem> sub_events;
	private String name;
	private int id;
	@SerializedName("is_featured")
	private boolean isFeatured;

	public int getTrending(){
		return trending;
	}

	public boolean isIsHoliday(){
		return isHoliday;
	}

	public int getAdmin(){
		return admin;
	}

	public String getVendorName(){
		return vendorName;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getEventSource(){
		return eventSource;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public int getSearchCount(){
		return searchCount;
	}

	public boolean isLiked(){
		return liked;
	}

	public List<SubEventsItem> getSubEvents(){
		return sub_events;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public boolean isIsFeatured(){
		return isFeatured;
	}
}