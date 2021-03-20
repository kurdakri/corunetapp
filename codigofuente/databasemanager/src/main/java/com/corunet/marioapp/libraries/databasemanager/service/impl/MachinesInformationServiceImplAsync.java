package com.corunet.marioapp.libraries.databasemanager.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.corunet.marioapp.libraries.databasemanager.service.IMachinesInformationService;
import com.corunet.marioapp.libraries.databasemanager.tasks.AsyncTaskManager;
import com.corunet.marioapp.libraries.global.exceptions.ApplicationException;
import com.corunet.marioapp.libraries.global.exceptions.NotFoundApplicationException;
import com.corunet.marioapp.libraries.global.model.Machine;

import lombok.extern.log4j.Log4j2;

@Service
@Profile("async")
@Log4j2
public class MachinesInformationServiceImplAsync implements IMachinesInformationService {
	
	private AsyncTaskManager asyncTaskManager;
	
	public MachinesInformationServiceImplAsync(AsyncTaskManager asyncTaskManager) {
		this.asyncTaskManager = asyncTaskManager;
	}

	@Override
	public Machine get(String ip, String hostname) throws ApplicationException, NotFoundApplicationException {
		log.warn("Se ha recibido una peticion get en modo asíncrono. Se ignora la peticion");
		throw new ApplicationException("La obtencion de registros se encuentra inhabilitada en este momento");
	}

	@Override
	public void insert(Machine machine) throws ApplicationException {
		log.info("Realizando llamada asincrona al insert.");
		try {
			asyncTaskManager.insert(machine);	
		}catch(Exception e) {
			throw new ApplicationException("Error llamando al metodo asincrono insert", e);
		}
		log.info("Llamada asincrona al insert enviada.");
	}

	@Override
	public void delete(Machine machine) throws ApplicationException {
		log.info("Realizando llamada asincrona al delete.");
		try {
			asyncTaskManager.delete(machine);
		}catch(Exception e) {
			throw new ApplicationException("Error llamando al metodo asíncrono delete", e);
		}
		log.info("Llamada asincrona al delete realizada correctamente.");
	}

}
