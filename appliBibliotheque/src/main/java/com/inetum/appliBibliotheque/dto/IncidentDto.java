package com.inetum.appliBibliotheque.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class IncidentDto {

	private Long id;	
	private String motif;
	
	public IncidentDto(Long id, String motif) {
		super();
		this.id = id;
		this.motif = motif;
	}

	public String getMotif() {
		return motif.toUpperCase();
	}
	
	
	
}
