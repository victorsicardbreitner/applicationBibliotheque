package com.inetum.appliBibliotheque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.appliBibliotheque.entity.Lecteur;

public interface DaoLecteur extends JpaRepository<Lecteur,Long> {

}
