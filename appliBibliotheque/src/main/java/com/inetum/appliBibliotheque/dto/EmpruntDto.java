package com.inetum.appliBibliotheque.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class EmpruntDto {

	private String nomLecteur;
	private String nomLivre;
	private String dateDebut; //mettre en format date ?
	private String dateFin;
	private String incident;
	
	//necessiterait de faire un DTO étendu
	private Long idLecteur;
	private Long idLivre;
	
	public EmpruntDto(String nomLecteur, String nomLivre, String dateDebut, String dateFin, String incident, Long idLecteur, Long idLivre) {
		this.nomLecteur = nomLecteur;
		this.nomLivre = nomLivre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.incident=incident;
		this.idLecteur=idLecteur;
		this.idLivre=idLivre;
	}
	
	public EmpruntDto(String nomLecteur, String nomLivre, String dateDebut, String dateFin) {
		this(nomLecteur,nomLivre,dateDebut,dateFin,"",null,null);
	}
	
	
	public void setNomLecteur(String setNomLecteur) {
		this.nomLecteur = setNomLecteur.toUpperCase();
	}
	




	
}
