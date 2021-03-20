package com.corunet.marioapp.libraries.tcpserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan({"com.corunet.marioapp.libraries.tcpserver.configuration",
				"com.corunet.marioapp.libraries.tcpserver.httpclient",
				"com.corunet.marioapp.libraries.tcpserver.listener",
				"com.corunet.marioapp.libraries.tcpserver.services",
				"com.corunet.marioapp.libraries.tcpserver.services.impl",
				"com.corunet.marioapp.libraries.tcpserver.tasks",
				"com.corunet.marioapp.libraries.global.exceptions",
				"com.corunet.marioapp.libraries.global.model",
				"com.corunet.marioapp.libraries.global.multithreading",
				"com.corunet.marioapp.libraries.protocol.enums",
				"com.corunet.marioapp.libraries.protocol.services",
				"com.corunet.marioapp.libraries.protocol.utils"})
public class TcpserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcpserverApplication.class, args);
	}

}
