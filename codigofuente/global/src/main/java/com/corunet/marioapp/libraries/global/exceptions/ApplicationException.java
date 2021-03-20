package com.corunet.marioapp.libraries.global.exceptions;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 3841025323076481996L;
	
	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(String message, Exception e) {
		super(message, e);
	}

}
