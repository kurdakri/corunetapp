package com.corunet.marioapp.libraries.global.exceptions;

public class ValidationException extends Exception {
	
	private static final long serialVersionUID = -8085920795078588082L;
	
	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(String message, Exception e) {
		super(message, e);
	}

}
