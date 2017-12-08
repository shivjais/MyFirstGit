package com.javabrains.RestTutorialFromJavaBrains.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectionDemoResource {

	@GET
	@Path("annotaions")
	public String getAnnotaion(@MatrixParam("param") String matrixParam,
					@HeaderParam("myheader") String headerValue,
					@CookieParam("Cookie_3") String cookieValue){
		
		return "Matrix param: "+matrixParam+" "+headerValue+" "+cookieValue;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo info,@Context HttpHeaders headers){
		//String uriInfo=info.getAbsolutePath().toString();
		//String uriInfo=info.getPath();
		//String headerInfo=headers.getAcceptableLanguages().toString();
		String headerInfo=headers.getCookies().toString();
		String uriInfo=info.getBaseUri().toString();
		
		return "uriInfo "+ uriInfo+ " headerInfo: "+ headerInfo;
	}
	
}
