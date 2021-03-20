package com.corunet.marioapp.libraries.tcpclient.tasks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.corunet.marioapp.libraries.tcpclient.service.IMessageService;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableScheduling
@Log4j2
public class SchedulerTask {
	
	private IMessageService messageService;
	
	public SchedulerTask(IMessageService messageService) {
		this.messageService = messageService;
	}
	
	@Value("${app.socket.port}")
	private int port;
	
	@Value("${app.socket.host}")
	private String hostname;
	
	@Scheduled(fixedDelay = 60000, initialDelay = 30000)
	public void scheduledTask() {
		log.info("Iniciando tarea programada en la fecha: {}", LocalDateTime.now());
		Socket socket = null;
		try {
			String message = messageService.getMessage();
			log.info("Estableciendo conexion con el servidor");
			socket = new Socket(hostname, port);
			DataOutputStream os = new DataOutputStream(socket.getOutputStream());
			os.writeUTF(message);
			log.info("Mensaje enviado");
			DataInputStream is = new DataInputStream(socket.getInputStream());
			log.info("Respuesta recibida del servidor: {}", is.readUTF());
		}catch(Exception e) {
			log.error("Se ha producido un error en la comunicacion con el servidor: ", e);
		}finally {
			try {
				socket.close();
			}catch(Exception e) {
				log.error("Error cerrando el socket");
			}
		}
		log.info("Finalizada la tarea programada en la fecha: {}", LocalDateTime.now());
	}

}
