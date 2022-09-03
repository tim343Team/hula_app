package com.hula.myapplication.dao;

public class CategoriesDao{
	private String name;
	private String createdAt;
	private String smallIcon;
	private int id;
	private String bgImage;

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getSmallIcon(){
		return smallIcon;
	}

	public int getId(){
		return id;
	}

	public String getBgImage(){
		return bgImage;
	}
}
