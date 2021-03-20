package com.corunet.marioapp.libraries.tcpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TcpclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcpclientApplication.class, args);
	}

}
