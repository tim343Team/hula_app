package com.hula.myapplication.dao;

import java.io.Serializable;

public class BrowsingCity implements Serializable {
	private String pinLocation;
	private String name;
	private int id;
	private int radius;

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
