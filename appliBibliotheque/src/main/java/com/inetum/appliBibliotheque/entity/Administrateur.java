package com.inetum.appliBibliotheque.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQuery(name="Admin.findById" , query="SELECT adm FROM Administrateur adm")

@Entity
@Getter @Setter @NoArgsConstructor
public class Administrateur extends Personne {
	

	private String username;
	private String password;

	
	
	@Override
	public String toString() {
		return "Administrateur [username=" + username + ", password=" + password + "]";
	}



	public Administrateur(Long id, String prenom, String nom, String email, String numtel, String numallee,
			String typevoie, String ville, String codepostal, String pays, String username, String password) {
		super(id, prenom, nom, email, numtel, numallee, typevoie, ville, codepostal, pays);
		this.username = username;
		this.password = password;
	}

	
	
	
	
	
	
}
