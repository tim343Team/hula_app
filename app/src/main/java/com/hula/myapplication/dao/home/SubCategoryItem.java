package com.hula.myapplication.dao.home;

public class SubCategoryItem{
	private String image;
	private boolean approved;
	private String name;
	private String createdAt;
	private int id;
	private Category category;
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

	public Category getCategory(){
		return category;
	}

	public Object getCreatedBy(){
		return createdBy;
	}
}
