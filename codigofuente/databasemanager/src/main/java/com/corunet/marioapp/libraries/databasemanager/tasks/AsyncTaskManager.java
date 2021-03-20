package com.corunet.marioapp.libraries.databasemanager.tasks;

import java.util.Objects;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.corunet.marioapp.libraries.databaselibrary.entity.MachinesInformation;
import com.corunet.marioapp.libraries.databasemanager.repository.MachinesInformationRepository;
import com.corunet.marioapp.libraries.global.model.Machine;

import lombok.extern.log4j.Log4j2;

@Component
@Profile("async")
@EnableAsync
@Log4j2
public class AsyncTaskManager {

	private MachinesInformationRepository machinesInformationRepository;
	
	public AsyncTaskManager(MachinesInformationRepository machinesInformationRepository) {
		this.machinesInformationRepository = machinesInformationRepository;
	}

	@Async("main-executor")
	public void insert(Machine machine) {
		log.info("Iniciando insert asíncrono");
		if(Objects.nonNull(machine) && Objects.nonNull(machine.getIp()) && Objects.nonNull(machine.getHostname())) {
			log.info("\tIp-{} Hostname-{}", machine.getIp(), machine.getHostname());
			try {
				MachinesInformation machinesInformation = new MachinesInformation();
				machinesInformation.setIp(machine.getIp());
				machinesInformation.setHostname(machine.getHostname());
				machinesInformation.setDescription(machine.getDescription());
				machinesInformationRepository.saveAndFlush(machinesInformation);	
			}catch(Exception e) {
				log.error("\tSe ha producido un error guardando el registro en BBDD", e);
			}
		}
		log.info("Finalizado el insert asíncrono");
	}
	
	@Async("main-executor")
	public void delete(Machine machine) {
		log.info("Iniciando delete asíncrono");
		if(Objects.nonNull(machine) && Objects.nonNull(machine.getIp()) && Objects.nonNull(machine.getHostname())) {
			log.info("\tIp-{} Hostname-{}", machine.getIp(), machine.getHostname());
			try {
				machinesInformationRepository.deleteByIpAndHostname(machine.getIp(), machine.getHostname());
			}catch(Exception e) {
				log.error("\tSe ha producido un error borrando el registro de BBDD", e);
			}	
		}
		log.info("Finalizado delete asíncrono");
	}
	
}
