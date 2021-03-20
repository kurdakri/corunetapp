package com.corunet.marioapp.libraries.global.exceptions;

public class NotFoundApplicationException extends Exception {

	private static final long serialVersionUID = -5539701067624407090L;
	
	public NotFoundApplicationException(String message) {
		super(message);
	}
	
	public NotFoundApplicationException(String message, Exception e) {
		super(message, e);
	}

}
