package com.inetum.appliBibliotheque.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class LivreDto {
	
	private Long id;
	private String titre;
	private String auteur;
	private Boolean dispo;
	private String etat;
	private String domaine;
	
	

	public void setTitre(String setTitre) {
		this.titre = setTitre.toUpperCase();
	}



	public LivreDto(Long id, String titre, String auteur, Boolean dispo, String etat, String domaine) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.dispo = dispo;
		this.etat = etat;
		this.domaine = domaine;
	}
	
	public LivreDto(Long id, String titre, String auteur, String etat) {
		this(id,titre,auteur,true,etat,null);
	}
	
	public LivreDto(String titre, String auteur) {
		this(null,titre,auteur,true,null,null);
	}

}
