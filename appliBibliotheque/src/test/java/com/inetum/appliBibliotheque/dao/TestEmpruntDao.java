package com.inetum.appliBibliotheque.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.inetum.appliBibliotheque.entity.Emprunt;
import com.inetum.appliBibliotheque.entity.Lecteur;
import com.inetum.appliBibliotheque.entity.Livre;
import com.inetum.appliBibliotheque.service.ServiceEmprunt;
import com.inetum.appliBibliotheque.service.ServiceLecteur;
import com.inetum.appliBibliotheque.service.ServiceLivre;

@SpringBootTest
@ActiveProfiles({"oracle"})
public class TestEmpruntDao {

	Logger logger = LoggerFactory.getLogger(TestEmpruntDao.class);
	@Autowired
	private ServiceLivre serviceLivre;

	@Autowired
	private ServiceLecteur serviceLecteur;

	@Autowired
	private ServiceEmprunt serviceEmprunt;

	@Test
	public void testFind() {
		serviceLivre.trouverTout();
	}

	@Test
	public void testEmpruntGetLivre() {

		Livre livre1 = serviceLivre.sauvegarder(new Livre(null, "Harry Potter 1", "JKR", true));
		Lecteur lecteur1 = serviceLecteur.sauvegarder(new Lecteur("Paul", "NomPaul"));

		Emprunt emprunt1 = new Emprunt(livre1, lecteur1);
		emprunt1 = serviceEmprunt.sauvegarder(emprunt1);
		
		//Pas besoin de fetch car cote One.
		Emprunt emprunt1Relu = serviceEmprunt.trouverParId(emprunt1.getId());

		assertEquals(emprunt1Relu.getLivre().getId(),livre1.getId());

	}

	@Test
	public void testTrivial() {
		assertTrue(true);
	}

}