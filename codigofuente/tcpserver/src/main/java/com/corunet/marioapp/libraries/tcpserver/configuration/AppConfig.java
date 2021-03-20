package com.corunet.marioapp.libraries.tcpserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class AppConfig {

	@Value("${app.socket.port}")
	private int port;
	
}
