package com.javabrains.RestTutorialFromJavaBrains.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.javabrains.RestTutorialFromJavaBrains.model.Comment;
import com.javabrains.RestTutorialFromJavaBrains.model.Message;
import com.javabrains.RestTutorialFromJavaBrains.model.Profile;
import com.javabrains.RestTutorialFromJavaBrains.resource.bean.MessageFilterBean;
import com.javabrains.RestTutorialFromJavaBrains.service.MessageService;

@Path("messages")
public class MessageResource {

	MessageService service=new MessageService();
	
	/*@GET
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(){
		return service.getAllMessages();
	}*/
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year")int year,
									@QueryParam("start")int start,
									@QueryParam("size")int size){
		if(year > 0){
			return service.getAllMessageForYear(year);
		}
		if(size > 0 &&  start >0){
			return service.getAllMessagePaginated(start, size);
		}
		return service.getAllMessages();
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear() > 0){
			return service.getAllMessageForYear(filterBean.getYear());
		}
		if(filterBean.getStart() > 0 &&  filterBean.getSize() >0){
			return service.getAllMessagePaginated(filterBean.getStart(), filterBean.getSize());
		}
		return service.getAllMessages();
	}
	
	/*@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message){
		return service.addMessage(message);
	}*/
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message,@Context UriInfo info){
		Message newMessage = service.addMessage(message);
		String newId=String.valueOf(newMessage.getId());
		URI uri=info.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage).build();
		//return service.addMessage(message);
	}
	
	/*@GET
	@Path("/{messageId}")
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id){
		return service.getMessage(id);
	}*/
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id,@Context UriInfo uriInfo){
	 Message message = service.getMessage(id);
	 message.addLink(getLinkForSelf(uriInfo, message), "self");
	 message.addLink(getLinkForProfile(uriInfo, message), "profile");
	 message.addLink(getLinkForComments(uriInfo, message), "comments");
	 return message;
	}

	private String getLinkForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
		 		.path(MessageResource.class)
		 		.path(MessageResource.class, "getCommentResource")
		 		.path(CommentResource.class)
		 		.resolveTemplate("messageId", message.getId())
		 		.build().toString();
		return uri;
	}

	private String getLinkForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
		 		.path(ProfileResource.class)
		 		.path(message.getAuthor())
		 		.build().toString();
		return uri;
	}

	private String getLinkForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
		 		.path(MessageResource.class)
		 		.path(String.valueOf(message.getId()))
		 		.build().toString();
		return uri;
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id,Message 					message){
		message.setId(id);
		return service.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteMessage(@PathParam("messageId") long id){
		return service.removeMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}
