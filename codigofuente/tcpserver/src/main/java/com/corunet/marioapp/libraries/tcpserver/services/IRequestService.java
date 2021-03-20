package com.corunet.marioapp.libraries.tcpserver.services;

import com.corunet.marioapp.libraries.global.model.Machine;

public interface IRequestService {

	public void processRequest(String operacion, Machine machine);
}
