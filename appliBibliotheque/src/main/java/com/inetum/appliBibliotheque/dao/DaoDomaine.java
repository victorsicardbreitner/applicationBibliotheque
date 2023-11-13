package com.inetum.appliBibliotheque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.appliBibliotheque.entity.Domaine;


public interface DaoDomaine extends JpaRepository<Domaine, Long>{
	Domaine findByIdFetchLivres(Long id);
}
