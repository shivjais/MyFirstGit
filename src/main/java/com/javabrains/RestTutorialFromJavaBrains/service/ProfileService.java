package com.javabrains.RestTutorialFromJavaBrains.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.javabrains.RestTutorialFromJavaBrains.database.DatabaseClass;
import com.javabrains.RestTutorialFromJavaBrains.model.Message;
import com.javabrains.RestTutorialFromJavaBrains.model.Profile;

public class ProfileService {

	private Map<String,Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("shiv", new Profile(1, "My profile","Shivam", "Jaiswal"));
	}
	public List<Profile> getAllProfiles(){
		return new ArrayList<>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return  profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfile(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfile().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfile(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
	
	
	
	
}
