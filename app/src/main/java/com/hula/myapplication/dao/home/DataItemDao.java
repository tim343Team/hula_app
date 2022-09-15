package com.hula.myapplication.dao.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataItemDao implements Anthing{
	private String section_type;
	private String title;
	private List<EventsItem> events;
	private boolean isGroupRelatedEvent;
	private boolean isFeatured;
	private boolean showLarge;

	public String getSectionType(){
		return section_type;
	}

	public String getTitle(){
		return title;
	}

	public List<EventsItem> getEvents(){
		if (events==null){
			events = new ArrayList<>();
		}
		return events;
	}

	public boolean isIsGroupRelatedEvent(){
		return isGroupRelatedEvent;
	}


	public boolean isIsFeatured(){
		return isFeatured;
	}

	public boolean isShowLarge(){
		return showLarge;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DataItemDao)) return false;
		DataItemDao that = (DataItemDao) o;
		return isGroupRelatedEvent == that.isGroupRelatedEvent && isFeatured == that.isFeatured && showLarge == that.showLarge && Objects.equals(section_type, that.section_type) && Objects.equals(title, that.title) && getEvents().size() == that.getEvents().size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(section_type, title, isGroupRelatedEvent, isFeatured, showLarge,getEvents().size());
	}
}