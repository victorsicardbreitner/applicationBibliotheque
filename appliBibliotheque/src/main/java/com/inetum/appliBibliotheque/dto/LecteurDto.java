package com.inetum.appliBibliotheque.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class LecteurDto {

	private String id;
	private String prenom; 
	private String nom;
	
	
	public LecteurDto(String id, String prenom, String nom) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom.toUpperCase(); //pour verifier qu'il s'agit bien du DTO
	}
	


}
