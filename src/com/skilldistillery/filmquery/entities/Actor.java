package com.skilldistillery.filmquery.entities;

import java.sql.ResultSet;
import java.util.List;

public class Actor {
	private int id;
	private String firstName;
	private String lastName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(firstName + " " + lastName + "");
				
		return sb.toString();
	}

	public Actor(int id, String firstName, String lastName) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Actor(int id) {

		this.id = id;
	
	}

	public Actor(String firstName, String lastName) {

		this.firstName = firstName;
		this.lastName = lastName;
	}

	

	

	

	

}
