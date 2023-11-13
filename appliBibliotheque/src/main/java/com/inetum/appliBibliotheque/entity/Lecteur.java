package com.inetum.appliBibliotheque.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQuery(name="Lecteur.findById" , query="SELECT lect FROM Lecteur lect")

@Entity
//@Table(name = "Lecteur")
@Getter @Setter @NoArgsConstructor
public class Lecteur extends Personne {
	
	
	public Lecteur(String prenom, String nom) {
		super(prenom, nom);
	}

	public Lecteur(Long id, String prenom, String nom, String email, String numtel, String numallee, String typevoie,
			String ville, String codepostal, String pays, Long idlecteur) {
		super(id, prenom, nom, email, numtel, numallee, typevoie, ville, codepostal, pays);
	}
	
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="lecteur")
	@JsonIgnore
	private List<Emprunt> emprunts;
	

	


	
	
	

	
	
}
