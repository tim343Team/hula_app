package com.hula.myapplication.dao.home;

import java.util.List;

public class RecommendedDao implements Anthing{
	private String section_type;
	private List<ProfilesItem> profiles;
	private String title;

	public String getSectionType(){
		return section_type;
	}

	public List<ProfilesItem> getProfiles(){
		return profiles;
	}

	public String getTitle(){
		return title;
	}
}