package com.corunet.marioapp.libraries.databasemanager.service.impl;

import java.util.Objects;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.corunet.marioapp.libraries.databaselibrary.entity.MachinesInformation;
import com.corunet.marioapp.libraries.databasemanager.repository.MachinesInformationRepository;
import com.corunet.marioapp.libraries.databasemanager.service.IMachinesInformationService;
import com.corunet.marioapp.libraries.global.exceptions.ApplicationException;
import com.corunet.marioapp.libraries.global.exceptions.NotFoundApplicationException;
import com.corunet.marioapp.libraries.global.exceptions.ValidationException;
import com.corunet.marioapp.libraries.global.model.Machine;

import lombok.extern.log4j.Log4j2;

@Service
@Profile("default")
@Log4j2
public class MachinesInformationServiceImplSync implements IMachinesInformationService {
	
	private MachinesInformationRepository machinesInformationRepository;
	
	public MachinesInformationServiceImplSync(MachinesInformationRepository machinesInformationRepository) {
		this.machinesInformationRepository = machinesInformationRepository;
	}

	@Override
	public Machine get(String ip, String hostname) throws ApplicationException, NotFoundApplicationException {
		log.info("Iniciado get sincrono");
		MachinesInformation machinesInformation = null;
		try {
			machinesInformation = machinesInformationRepository.findByIpAndHostname(ip, hostname);	
		}catch(Exception e) {
			throw new NotFoundApplicationException("Error no controlado recuperando la máquina de BBDD", e);
		}
		if(Objects.isNull(machinesInformation)) {
			throw new NotFoundApplicationException("No se han recuperado registros de BBDD");
		}
		log.debug("Registro recuperado -> Ip:{} - Hostname:{} - Descripcion:{}", machinesInformation.getIp(), machinesInformation.getHostname(), machinesInformation.getDescription());
		log.info("Finalizado get sincrono");
		return new Machine(machinesInformation.getHostname(), machinesInformation.getIp(), machinesInformation.getDescription());
	}

	@Override
	public void insert(Machine machine) throws ValidationException, ApplicationException {
		log.info("Iniciado insert sincrono");
		validate(machine);
		MachinesInformation machinesInformation = new MachinesInformation();
		machinesInformation.setIp(machine.getIp());
		machinesInformation.setHostname(machine.getHostname());
		machinesInformation.setDescription(machine.getDescription());
		try {
			machinesInformationRepository.saveAndFlush(machinesInformation);
			log.debug("Guardado correctamente el registro -> Ip:{} - Hostname:{} - Descripcion:{}", machinesInformation.getIp(), machinesInformation.getHostname(), machinesInformation.getDescription());
		}catch(Exception e) {
			throw new ApplicationException("Error no controlado guardando el registro en BBDD");
		}
		log.info("Finalizado insert sincrono");
	}

	@Override
	public void delete(Machine machine) throws ValidationException, ApplicationException  {
		log.info("Iniciando delete sincrono");
		validate(machine);
		try {
			machinesInformationRepository.deleteByIpAndHostname(machine.getIp(), machine.getHostname());	
		}catch(Exception e) {
			throw new ApplicationException("Error no controlado eliminando el registro de BBDD");
		}
		log.debug("Borrado correctamente el registro -> Ip:{} - Hostname:{}", machine.getIp(), machine.getHostname());
		log.info("Finalizado el delete sincrono");
	}
	
	private void validate(Machine machine) throws ValidationException {
		log.info("Iniciando validacion de la peticion");
		if(Objects.isNull(machine)) {
			throw new ValidationException("Error de validacion: El objeto no puede ser nulo");
		}
		if(Objects.isNull(machine.getIp()) || Objects.isNull(machine.getHostname())) {
			throw new ValidationException("Error de validacion: Los campos Ip y Hostname deben estar informados");
		}
		log.info("Validacion realizada correctamente.");
	}

}
