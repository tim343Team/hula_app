package com.hula.myapplication.dao;

public class CityDao {
	private String pinLocation;
	private String name;
	private int id;
	private int radius;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPinLocation(String pinLocation) {
		this.pinLocation = pinLocation;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getPinLocation(){
		return pinLocation;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public int getRadius(){
		return radius;
	}
}
