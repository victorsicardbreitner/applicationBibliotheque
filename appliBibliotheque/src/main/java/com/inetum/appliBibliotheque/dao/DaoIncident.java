package com.inetum.appliBibliotheque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.appliBibliotheque.entity.Incident;

public interface DaoIncident extends JpaRepository<Incident,Long>{

}
//List<Emprunt> findByTitre(String titre);

