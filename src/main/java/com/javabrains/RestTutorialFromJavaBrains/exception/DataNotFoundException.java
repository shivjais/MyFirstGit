package com.javabrains.RestTutorialFromJavaBrains.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1336229911578292417L;

	
public DataNotFoundException(String message){
	super(message);
}
}
