package com.corunet.marioapp.libraries.databaselibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="machinesinfo")
@Getter
@Setter
public class MachinesInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "hostname", length = 15)
	private String hostname;
	
	@Column(name = "ip", length = 12)
	private String ip;
	
	@Column(name = "description", length = 100)
	private String description;
	
}
