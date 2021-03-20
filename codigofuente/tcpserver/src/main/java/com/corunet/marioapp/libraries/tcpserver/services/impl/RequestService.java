package com.corunet.marioapp.libraries.tcpserver.services.impl;

import org.springframework.stereotype.Service;

import com.corunet.marioapp.libraries.global.model.Machine;
import com.corunet.marioapp.libraries.protocol.utils.ProtocolConstants;
import com.corunet.marioapp.libraries.tcpserver.httpclient.MachinesRest;
import com.corunet.marioapp.libraries.tcpserver.services.IRequestService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RequestService implements IRequestService {
	
	public MachinesRest machinesRest;
	
	public RequestService(MachinesRest machinesRest) {
		this.machinesRest = machinesRest;
	}

	@Override
	public void processRequest(String operacion, Machine machine) {
		log.info("Recibida operacion de tipo {}", operacion);
		switch(operacion) {
		case ProtocolConstants.GET:
			machinesRest.getMachineInfo(machine);
			break;
		case ProtocolConstants.POST:
			machinesRest.postMachineInfo(machine);
			break;
		case ProtocolConstants.DELETE:
			machinesRest.deleteMachineInfo(machine);
			break;
		default:
			log.error("La operacion recibida no es valida.");
		}
	}

}
