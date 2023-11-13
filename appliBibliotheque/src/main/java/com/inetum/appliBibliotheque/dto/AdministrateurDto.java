package com.inetum.appliBibliotheque.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class AdministrateurDto {

	private String id;
	private String prenom; 
	private String nom;
	
	
	public AdministrateurDto(String id, String prenom, String nom) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom.toUpperCase(); //pour verifier qu'il s'agit bien du DTO
	}
	
	

}
