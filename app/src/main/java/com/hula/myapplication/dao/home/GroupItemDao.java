package com.hula.myapplication.dao.home;

import java.util.List;

public class GroupItemDao{
	private int user_num;
	private int size;
	private String name;
	private String description;
	private List<AdminItem> admin;
	private int id;
	private GroupEvent event;
	private List<UsersItem> users;

	public int getUserNum(){
		return user_num;
	}

	public int getSize(){
		return size;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public List<AdminItem> getAdmin(){
		return admin;
	}

	public int getId(){
		return id;
	}

	public GroupEvent getEvent(){
		return event;
	}

	public List<UsersItem> getUsers(){
		return users;
	}
}