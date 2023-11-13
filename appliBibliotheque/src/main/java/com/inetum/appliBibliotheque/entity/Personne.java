package com.inetum.appliBibliotheque.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQuery(name="Personne.findByPrenom" , query="SELECT p FROM Personne p WHERE p.prenom = ?1")
@NamedQuery(name="Personne.findByNom" , query="SELECT p FROM Personne p WHERE p.nom = ?1")
@NamedQuery(name="Personne.findByEmail" , query="SELECT p FROM Personne p WHERE p.email = ?1") 
@NamedQuery(name="Personne.findByNumtel" , query="SELECT p FROM Personne p WHERE p.numtel = ?1") 
@NamedQuery(name="Personne.findByVille" , query="SELECT p FROM Personne p WHERE p.ville = ?1") 
@NamedQuery(name="Personne.findByPays" , query="SELECT p FROM Personne p WHERE p.pays = ?1") 

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Role" , discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "Personne") //valeur pour colonne "typePersonne"
@Getter @Setter @NoArgsConstructor
public class Personne {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String prenom;
	private String nom;
	private String email;
	private String numtel;
	private String numallee;
	private String typevoie;
	private String ville;
	private String codepostal;
	private String pays;


	@Override
	public String toString() {
		return "Personne [prenom=" + prenom + ", nom=" + nom + ", email=" + email + ", numtel=" + numtel
				+ ", numallee=" + numallee + ", typevoie=" + typevoie
				+ ", ville=" + ville + ", codepostal=" + codepostal + ", pays=" + pays + "]";
	}


	public Personne(Long id, String prenom, String nom, String email, String numtel, String numallee, String typevoie,
			String ville, String codepostal, String pays) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.numtel = numtel;
		this.numallee = numallee;
		this.typevoie = typevoie;
		this.ville = ville;
		this.codepostal = codepostal;
		this.pays = pays;
	}
	
	//constructeur simple
	public Personne(String prenom, String nom) {
		this.prenom = prenom;
		this.nom = nom;
	}



	
}
