package com.javabrains.RestTutorialFromJavaBrains.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Profile {

	private long id;
	private String profile;
	private String firstName;
	private String lastName;
	private Date created;
	
	public Profile(){
		
	}
	
	public Profile(long id, String profile, String firstName, String lastName) {
	
		this.id = id;
		this.profile = profile;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
