package com.hula.myapplication.dao;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class CategoriesDao implements Serializable {
	private String name;
	private String created_at;
	private String small_icon;
	private int id;
	private String bg_image;

	public String getName(){
		return name;
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

	public String getSmall_icon() {
		return small_icon;
	}

	public void setSmall_icon(String small_icon) {
		this.small_icon = small_icon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBg_image() {
		return bg_image;
	}

	public void setBg_image(String bg_image) {
		this.bg_image = bg_image;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CategoriesDao)) return false;
		CategoriesDao that = (CategoriesDao) o;
		return id == that.id && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, id);
	}
}
