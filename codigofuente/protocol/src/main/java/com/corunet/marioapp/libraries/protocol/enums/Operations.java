package com.corunet.marioapp.libraries.protocol.enums;

public enum Operations {
	POST("01","POST"),
	GET("02", "GET"),
	DELETE("03", "DELETE");
	
	private final String code;
	
	private final String operation;
	
	private Operations(String code, String operation) {
		this.code = code;
		this.operation = operation;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getOperation() {
		return operation;
	}

}
