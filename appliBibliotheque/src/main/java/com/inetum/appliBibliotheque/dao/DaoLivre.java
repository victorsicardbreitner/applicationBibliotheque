package com.inetum.appliBibliotheque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.appliBibliotheque.entity.Livre;

public interface DaoLivre extends JpaRepository<Livre,Long>{
	List<Livre> findByTitre(String titre);

	Livre findByIdFetchEmprunts(Long id);
}
