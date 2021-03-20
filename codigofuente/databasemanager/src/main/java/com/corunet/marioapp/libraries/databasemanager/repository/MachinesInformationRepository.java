package com.corunet.marioapp.libraries.databasemanager.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corunet.marioapp.libraries.databaselibrary.entity.MachinesInformation;

public interface MachinesInformationRepository extends JpaRepository<MachinesInformation, Long> {
	
	public MachinesInformation findByIpAndHostname(String ip, String hostname);
	
	@Transactional
	public void deleteByIpAndHostname(String ip, String hostname);

}
