package com.javabrains.RestTutorialFromJavaBrains.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.javabrains.RestTutorialFromJavaBrains.model.ErrorMessage;
//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(), 500, "www.google.co.in");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage).build();
	}

}
