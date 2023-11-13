package com.inetum.appliBibliotheque.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.appliBibliotheque.entity.Emprunt;
import com.inetum.appliBibliotheque.entity.EmpruntCompositePk;
import com.inetum.appliBibliotheque.entity.Lecteur;

public interface DaoEmprunt extends JpaRepository<Emprunt, EmpruntCompositePk>{
	
	List<Emprunt> findByLecteur(Lecteur lecteur);
	
	List<Emprunt> findByLecteurAndDateFinAfter(Lecteur lecteur,Date date);
	


}
//List<Emprunt> findByTitre(String titre);