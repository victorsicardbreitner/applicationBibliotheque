package com.inetum.appliBibliotheque.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class DomaineDto {

	private Long id;
	private String nom;
	private String description;
	
	
	public DomaineDto(Long id, String nom, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
	}


	public String getNom() {
		return nom.toUpperCase(); //pour verifier qu'il s'agit du DTO
	}
	
	
	

}
