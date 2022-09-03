package com.hula.myapplication.dao;

import java.util.Objects;

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
