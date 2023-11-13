package com.inetum.appliBibliotheque.entity;

import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQuery(
		name="Domaine.findByIdFetchLivres" , 
		query="SELECT dom FROM Domaine dom LEFT JOIN FETCH dom.livres liv WHERE dom.id = ?1" //attention sensible à la case
)
@Getter @Setter @NoArgsConstructor
public class Domaine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	private String description;

	public Domaine(Long id, String nom, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
	}
	
	public Domaine(String nom, String description) {
		this(null, nom, description);
	}
	
	@JsonIgnore 
 	@OneToMany(fetch=FetchType.LAZY,mappedBy="domaine")
	private List<Livre> livres;
		
}
