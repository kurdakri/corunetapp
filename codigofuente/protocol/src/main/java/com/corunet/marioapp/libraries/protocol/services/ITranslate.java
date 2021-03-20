package com.corunet.marioapp.libraries.protocol.services;

public interface ITranslate {

	public String translateOperation(String message);
	
	public String translateIP(String message);
	
	public String translateHostname(String message);
	
	public String translateDescription(String message);
	
}
