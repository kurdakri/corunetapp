package com.corunet.marioapp.libraries.global.multithreading;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableAsync
@Log4j2
public class MultithreadingConfig {

	@Value("${app.poolconfig.coresize}")
	private String coreSize;
	
	@Value("${app.poolconfig.maxsize}")
	private String maxSize;
	
	@Bean("main-executor")
	public TaskExecutor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(Integer.parseInt(coreSize));
		executor.setMaxPoolSize(Integer.parseInt(maxSize));
		executor.initialize();
		log.info("Inicializado main-executor con {} hilos de core y un maximo de {} hilos.", coreSize, maxSize);
		return executor;
	}
	
}
