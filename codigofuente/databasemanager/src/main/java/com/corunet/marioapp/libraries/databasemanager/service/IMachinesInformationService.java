package com.corunet.marioapp.libraries.databasemanager.service;

import com.corunet.marioapp.libraries.global.exceptions.ApplicationException;
import com.corunet.marioapp.libraries.global.exceptions.NotFoundApplicationException;
import com.corunet.marioapp.libraries.global.exceptions.ValidationException;
import com.corunet.marioapp.libraries.global.model.Machine;

public interface IMachinesInformationService {

	public Machine get(String ip, String hostname) throws ApplicationException, NotFoundApplicationException;
	
	public void insert(Machine machine) throws ValidationException, ApplicationException;
	
	public void delete(Machine machine) throws ValidationException, ApplicationException;
	
}
