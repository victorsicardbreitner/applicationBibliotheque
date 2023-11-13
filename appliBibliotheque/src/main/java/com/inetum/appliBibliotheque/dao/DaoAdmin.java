package com.inetum.appliBibliotheque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.appliBibliotheque.entity.Administrateur;

public interface DaoAdmin extends JpaRepository<Administrateur, Long> {

}
