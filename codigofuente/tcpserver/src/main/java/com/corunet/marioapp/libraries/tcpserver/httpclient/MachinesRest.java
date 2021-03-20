package com.corunet.marioapp.libraries.tcpserver.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.corunet.marioapp.libraries.global.model.Machine;

@FeignClient(name = "databasemanager", url="http://localhost:8085/api/v1/machines/")
public interface MachinesRest {

	@PostMapping("find")
	public ResponseEntity getMachineInfo(@RequestBody Machine machine);
	
	@PostMapping("insert")
	public ResponseEntity postMachineInfo(@RequestBody Machine machine);
	
	@DeleteMapping("drop")
	public ResponseEntity deleteMachineInfo(@RequestBody Machine machine);
	
}
