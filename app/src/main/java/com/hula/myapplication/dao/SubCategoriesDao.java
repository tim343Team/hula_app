package com.hula.myapplication.dao;

public class SubCategoriesDao{
	private String image;
	private boolean approved;
	private String name;
	private String createdAt;
	private int id;
	private CategoriesDao category;
	private Object createdBy;

	public String getImage(){
		return image;
	}

	public boolean isApproved(){
		return approved;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public CategoriesDao getCategory() {
		return category;
	}

	public Object getCreatedBy(){
		return createdBy;
	}
}
