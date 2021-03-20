package com.corunet.marioapp.libraries.databasemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.corunet.marioapp.libraries.databasemanager.config",
				"com.corunet.marioapp.libraries.databasemanager.controller",
				"com.corunet.marioapp.libraries.databasemanager.repository",
				"com.corunet.marioapp.libraries.databasemanager.service",
				"com.corunet.marioapp.libraries.databasemanager.service.impl",
				"com.corunet.marioapp.libraries.databasemanager.tasks",
				"com.corunet.marioapp.libraries.global.exceptions",
				"com.corunet.marioapp.libraries.global.model",
				"com.corunet.marioapp.libraries.global.multithreading"})
@EntityScan({"com.corunet.marioapp.libraries.databaselibrary.entity"})
public class DatabasemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabasemanagerApplication.class, args);
	}

}
