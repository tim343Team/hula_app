package com.hula.myapplication.dao.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataItemDao implements Anthing{
	private String sectionType;
	private String title;
	private List<EventsItem> events;
	private boolean isGroupRelatedEvent;
	private String sectionTyp;
	private boolean isFeatured;
	private boolean showLarge;

	public String getSectionType(){
		return sectionType;
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

	public String getSectionTyp(){
		return sectionTyp;
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
		return isGroupRelatedEvent == that.isGroupRelatedEvent && isFeatured == that.isFeatured && showLarge == that.showLarge && Objects.equals(sectionType, that.sectionType) && Objects.equals(title, that.title) && Objects.equals(sectionTyp, that.sectionTyp) && getEvents().size() == that.getEvents().size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(sectionType, title, isGroupRelatedEvent, sectionTyp, isFeatured, showLarge,getEvents().size());
	}
}