package com.corunet.marioapp.libraries.databasemanager.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corunet.marioapp.libraries.databasemanager.service.IMachinesInformationService;
import com.corunet.marioapp.libraries.global.exceptions.ApplicationException;
import com.corunet.marioapp.libraries.global.exceptions.NotFoundApplicationException;
import com.corunet.marioapp.libraries.global.exceptions.ValidationException;
import com.corunet.marioapp.libraries.global.model.Machine;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("${app.datasources.machines}")
@Log4j2
public class MachinesController {
	
	@Value("${app.locale}")
	private String locale;
	
	private IMachinesInformationService machinesInformationService;
	
	private ResourceBundleMessageSource messageSource;
	
	public MachinesController(IMachinesInformationService machinesInformationService, ResourceBundleMessageSource messageSource) {
		this.machinesInformationService = machinesInformationService;
		this.messageSource = messageSource;
	}
	
	@PostMapping("${app.datasources.get}")
	public ResponseEntity getMachineInfo(@RequestBody Machine machine){
		log.info("Recibida peticion para obtener maquina");
		Machine recoveredMachine = null;
		try {
			recoveredMachine = machinesInformationService.get(machine.getIp(), machine.getHostname());
		} catch (NotFoundApplicationException e) {
			log.warn(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage("NOT_FOUND", null, new Locale(locale)));
		} catch (ApplicationException ex) {
			log.error(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("INTERNAL_SERVER_ERROR", null, new Locale(locale)));
		} catch (Exception exc){
			log.error("Se ha producido un error no controlado: ", exc);
			return ResponseEntity.badRequest().body(messageSource.getMessage("BAD_REQUEST", null, new Locale(locale)));
		}
		log.info("Maquina recuperada correctamente");
		return ResponseEntity.ok(recoveredMachine);
	}
	
	@PostMapping("${app.datasources.post}")
	public ResponseEntity postMachineInfo(@RequestBody Machine machine){
		log.info("Recibida peticion para insertar maquina");
		try {
			machinesInformationService.insert(machine);
		} catch (ValidationException e) {
			log.warn(e);
			return ResponseEntity.badRequest().body(messageSource.getMessage("BAD_REQUEST", null, new Locale(locale)));
		} catch (ApplicationException ex) {
			log.error(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("INTERNAL_SERVER_ERROR", null, new Locale(locale)));
		} catch (Exception exc) {
			log.error("Se ha producido un error no controlado: ", exc);
			return ResponseEntity.badRequest().body(messageSource.getMessage("BAD_REQUEST", null, new Locale(locale)));
		}
		log.info("Peticion procesada correctamente");
		return ResponseEntity.status(HttpStatus.CREATED).body(messageSource.getMessage("INSERT_OK", null, new Locale(locale)));
	}
	
	@DeleteMapping("${app.datasources.delete}")
	public ResponseEntity deleteMachineInfo(@RequestBody Machine machine){
		log.info("Recibida peticion para borrar maquina");
		try {
			machinesInformationService.delete(machine);
		} catch (ValidationException e) {
			log.warn(e);
			return ResponseEntity.badRequest().body(messageSource.getMessage("BAD_REQUEST", null, new Locale(locale)));
		} catch (ApplicationException ex) {
			log.error(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("INTERNAL_SERVER_ERROR", null, new Locale(locale)));
		} catch (Exception exc) {
			log.error("Se ha producido un error no controlado: ", exc);
			return ResponseEntity.badRequest().body(messageSource.getMessage("BAD_REQUEST", null, new Locale(locale)));
		}
		log.info("Peticion procesada correctamente");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(messageSource.getMessage("DELETE_OK", null, new Locale(locale)));
	}

}
