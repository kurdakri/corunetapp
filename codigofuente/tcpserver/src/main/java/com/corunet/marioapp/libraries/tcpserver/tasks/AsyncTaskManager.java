package com.corunet.marioapp.libraries.tcpserver.tasks;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.corunet.marioapp.libraries.global.model.Machine;
import com.corunet.marioapp.libraries.protocol.enums.Operations;
import com.corunet.marioapp.libraries.protocol.services.ITranslate;
import com.corunet.marioapp.libraries.tcpserver.services.IRequestService;

import lombok.extern.log4j.Log4j2;

@Component
@EnableAsync
@Log4j2
public class AsyncTaskManager {

	private ITranslate translate;
	
	private IRequestService requestService;
	
	public AsyncTaskManager(ITranslate translate, IRequestService requestService) {
		this.translate = translate;
		this.requestService = requestService;
	}
	
	@Async("main-executor")
	public void process(String message) {
		log.info("Iniciando el procesado del mensaje recibido");
		try {
			//Parsear el mensaje para obtener operacion y generar el objeto de tipo Machine
			String operacion = translate.translateOperation(message);
			String ip = translate.translateIP(message);
			String hostname = translate.translateHostname(message);
			String descripcion = translate.translateDescription(message);
			Machine machine = new Machine(hostname, ip, descripcion);
			//Enviar peticion HTTP
			String action = getAction(operacion);
			requestService.processRequest(action, machine);
		}catch(Exception e) {
			log.error("Excepcion no controlada procesando el mensaje recibido", e);
		}
		log.info("Fin del procesado del mensaje recibido");
	}
	
	private String getAction(String operacion) {
		String action = null;
		Operations[] operations = Operations.values();
		for(int i = 0; i < operations.length; i++) {
			if(operations[i].getCode().equals(operacion)) {
				action = operations[i].getOperation();
				break;
			}
		}
		return action;
	}
	
}
