package com.hula.myapplication.dao;

import java.io.Serializable;

public class User implements Serializable {
	private String last_name;
	private int id;
	private String first_name;
	private String email;
	private String username;

	public String getLastName(){
		return last_name;
	}

	public int getId(){
		return id;
	}

	public String getFirstName(){
		return first_name;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}
}
