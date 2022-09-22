package com.hula.myapplication.dao;

import java.io.Serializable;
import java.util.Objects;

public class SubCategoriesDao implements Serializable {
	private String image;
	private boolean approved;
	private String name;
	private String created_at;
	private int id;
	private CategoriesDao category;
	private Object created_by;

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


	public int getId(){
		return id;
	}

	public CategoriesDao getCategory() {
		return category;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCategory(CategoriesDao category) {
		this.category = category;
	}

	public Object getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Object created_by) {
		this.created_by = created_by;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SubCategoriesDao)) return false;
		SubCategoriesDao that = (SubCategoriesDao) o;
		return approved == that.approved && id == that.id && Objects.equals(image, that.image) && Objects.equals(name, that.name) && Objects.equals(created_at, that.created_at) && Objects.equals(category, that.category) && Objects.equals(created_by, that.created_by);
	}


}
