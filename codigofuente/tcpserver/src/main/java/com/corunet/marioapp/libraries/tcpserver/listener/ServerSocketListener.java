package com.corunet.marioapp.libraries.tcpserver.listener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corunet.marioapp.libraries.tcpserver.configuration.AppConfig;
import com.corunet.marioapp.libraries.tcpserver.tasks.AsyncTaskManager;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ServerSocketListener implements CommandLineRunner {

	private AppConfig appConfig;

	private AsyncTaskManager asyncTaskManager;

	public ServerSocketListener(AsyncTaskManager asyncTaskManager, AppConfig appConfig) {
		this.asyncTaskManager = asyncTaskManager;
		this.appConfig = appConfig;
	}

	@Override
	public void run(String... args) throws Exception {
		boolean run = true;
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			log.info("Iniciando escucha en el puerto {}", appConfig.getPort());
			serverSocket = new ServerSocket(appConfig.getPort());
			while(run) {
				log.info("Esperando conexiones");
				socket = serverSocket.accept();
				log.info("Aceptada conexion entrante.");
				DataInputStream input = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				String message = input.readUTF();
				log.info("Mensaje recibido: {}", message);
				asyncTaskManager.process(message);
				out.writeUTF("OK");
				out.flush();	
			}
		}catch(Exception e) {
			log.error("Se ha producido un error no controlado y se cerrará la conexión:", e);
		}finally {
			try {
				serverSocket.close();	
			}catch(Exception e) {
				log.warn("El serversocket no se cierra.");
			}
			try {
				socket.close();
			}catch(Exception e) {
				log.warn("El objeto socket no se cierra.");
			}
		}
	}
}
