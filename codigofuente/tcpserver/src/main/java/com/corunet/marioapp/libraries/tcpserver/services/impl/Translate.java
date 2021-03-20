package com.corunet.marioapp.libraries.tcpserver.services.impl;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.corunet.marioapp.libraries.protocol.services.ITranslate;
import com.corunet.marioapp.libraries.protocol.utils.ProtocolConstants;

@Service
@Scope("prototype")
public class Translate implements ITranslate {

	@Override
	public String translateOperation(String message) {
		return StringUtils.substring(message, 0, 2);
	}

	@Override
	public String translateIP(String message) {
		String ip = null;
		if(StringUtils.contains(message, ProtocolConstants.IP)) {
			String[] splitted = StringUtils.split(message, ProtocolConstants.IP);
			if(Objects.nonNull(splitted[1])) {
				if(StringUtils.contains(splitted[1], ProtocolConstants.HOST)){
					String[] splitted2 = StringUtils.split(splitted[1], ProtocolConstants.HOST);
					if(Objects.nonNull(splitted2[0])) {
						ip = splitted2[0];
					}
				}else if(StringUtils.contains(splitted[1], ProtocolConstants.DESCRIPCION)) {
					String[] splitted2 = StringUtils.split(splitted[1], ProtocolConstants.DESCRIPCION);
					if(Objects.nonNull(splitted2[0])) {
						ip = splitted2[0];
					}
				}else {
					ip = splitted[1];
				}
			}
		}
		return ip;
	}

	@Override
	public String translateHostname(String message) {
		String hostname = null;
		if(StringUtils.contains(message, ProtocolConstants.HOST)) {
			String[] splitted = StringUtils.split(message, ProtocolConstants.HOST);
			if(Objects.nonNull(splitted[1])) {
				if(StringUtils.contains(splitted[1], ProtocolConstants.DESCRIPCION)) {
					String[] splitted2 = StringUtils.split(splitted[1], ProtocolConstants.DESCRIPCION);
					if(Objects.nonNull(splitted2[0])) {
						hostname = splitted2[0];
					}
				}else {
					hostname = splitted[1];
				}
			}
		}
		return hostname;
	}

	@Override
	public String translateDescription(String message) {
		String descripcion = null;
		if(StringUtils.contains(message, ProtocolConstants.DESCRIPCION)) {
			String[] splitted = StringUtils.split(message, ProtocolConstants.DESCRIPCION);
			if(Objects.nonNull(splitted[1])) {
				descripcion = splitted[1];
			}
		}
		return descripcion;
	}


}
