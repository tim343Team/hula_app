package com.hula.myapplication.dao;

import java.util.Objects;

public class SubCategoriesDao{
	private String image;
	private boolean approved;
	private String name;
	private String createdAt;
	private int id;
	private CategoriesDao category;
	private Object createdBy;

	public SubCategoriesDao() {
	}

	public SubCategoriesDao(String name) {
		this.name = name;
	}

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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SubCategoriesDao)) return false;
		SubCategoriesDao that = (SubCategoriesDao) o;
		return approved == that.approved && id == that.id && Objects.equals(image, that.image) && Objects.equals(name, that.name) && Objects.equals(createdAt, that.createdAt) && Objects.equals(category, that.category) && Objects.equals(createdBy, that.createdBy);
	}

	@Override
	public int hashCode() {
		return Objects.hash(image, approved, name, createdAt, id, category, createdBy);
	}
}
