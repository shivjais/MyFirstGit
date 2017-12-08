package com.javabrains.RestTutorialFromJavaBrains.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.javabrains.RestTutorialFromJavaBrains.model.Message;
import com.javabrains.RestTutorialFromJavaBrains.model.Profile;
import com.javabrains.RestTutorialFromJavaBrains.service.MessageService;
import com.javabrains.RestTutorialFromJavaBrains.service.ProfileService;

@Path("profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

ProfileService service=new ProfileService();

	/*@GET
	public List<Profile> getProfiles(){
		return service.getAllProfiles();
	}*/
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Profile> getProfilesforXml(){
		return service.getAllProfiles();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getProfilesForJson(){
		return service.getAllProfiles();
	}
	
	@POST
	public Profile addProfile(Profile profile){
		return service.addProfile(profile);
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName){
		return service.getProfile(profileName);
	}
	
	/*@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName,Profile profile){
		profile.setProfile(profileName);
		return service.updateProfile(profile);
	}*/
	@PUT
	@Path("/{profileName}")
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile updateProfileforXML(@PathParam("profileName") String profileName,Profile profile){
		System.out.println("produce JSON");
		profile.setProfile(profileName);
		return service.updateProfile(profile);
	}
	@PUT
	@Path("/{profileName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_XML)
	public Profile updateProfileForJson(@PathParam("profileName") String profileName,Profile profile){
		System.out.println("produce xml");
		profile.setProfile(profileName);
		return service.updateProfile(profile);
	}
	@DELETE
	@Path("/{profileName}")
	public Profile deleteProfile(@PathParam("profileName") String profileName){
		return service.removeProfile(profileName);
	}
}
