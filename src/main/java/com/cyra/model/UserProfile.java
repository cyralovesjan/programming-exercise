package com.cyra.model;

import java.io.Serializable;

public class UserProfile implements Serializable {

	private static final long serialVersionUID = -4550230756931316649L;
	private String company;
	private String name;
	private String position;

	public UserProfile(String company, String name, String position) {
		super();
		this.company = company;
		this.name = name;
		this.position = position;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
