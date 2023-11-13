package com.inetum.appliBibliotheque.service;

import java.util.Date;
import java.util.List;

import com.inetum.appliBibliotheque.dto.EmpruntDto;
import com.inetum.appliBibliotheque.entity.Emprunt;
import com.inetum.appliBibliotheque.entity.EmpruntCompositePk;
import com.inetum.appliBibliotheque.entity.Lecteur;

public interface ServiceEmprunt extends GenericService<Emprunt,EmpruntCompositePk,EmpruntDto>{
	
	
	List<Emprunt> trouverParLecteurEtDateFinApres(Lecteur lecteur, Date date);
	
	List<Emprunt> trouverParLecteurEtDateFinApresMaintenant(Lecteur lecteur);
	
	
	List<Emprunt> trouverParLecteur(Lecteur lecteur);
	List<EmpruntDto> trouverParLecteurDto(Lecteur lecteur);

	

	

}
