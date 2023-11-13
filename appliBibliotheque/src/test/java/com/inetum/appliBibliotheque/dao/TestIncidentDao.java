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
import com.inetum.appliBibliotheque.entity.Incident;
import com.inetum.appliBibliotheque.entity.Lecteur;
import com.inetum.appliBibliotheque.entity.Livre;
import com.inetum.appliBibliotheque.service.ServiceEmprunt;
import com.inetum.appliBibliotheque.service.ServiceIncident;
import com.inetum.appliBibliotheque.service.ServiceLecteur;
import com.inetum.appliBibliotheque.service.ServiceLivre;

@SpringBootTest
@ActiveProfiles({"oracle"})
public class TestIncidentDao {

	Logger logger = LoggerFactory.getLogger(TestIncidentDao.class);
	@Autowired
	private ServiceLivre serviceLivre;

	@Autowired
	private ServiceLecteur serviceLecteur;

	@Autowired
	private ServiceEmprunt serviceEmprunt;
	
	@Autowired
	private ServiceIncident serviceIncident;

	@Test
	public void testFind() {
		serviceIncident.trouverTout();
	}

	@Test
	public void testIncidentGetEmprunt() {

		Livre livre1 = serviceLivre.sauvegarder(new Livre(null, "Harry Potter 1", "JKR", true));
		Lecteur lecteur1 = serviceLecteur.sauvegarder(new Lecteur("Paul", "NomPaul"));
		Incident incident1 = serviceIncident.sauvegarder(new Incident("non rendu"));
		
		Emprunt emprunt1 = new Emprunt(livre1, lecteur1);
		emprunt1.setIncident(incident1);
		emprunt1 = serviceEmprunt.sauvegarder(emprunt1);
		
		//Pas besoin de fetch car OneToOne
		Incident incident1Relu = serviceIncident.trouverParId(incident1.getId());

		assertEquals(incident1Relu.getEmprunt().getId(),emprunt1.getId());
		

		Emprunt emprunt1Relu = serviceEmprunt.trouverParId(emprunt1.getId());
		assertEquals(emprunt1Relu.getIncident().getId(),incident1.getId());
		

	}

	@Test
	public void testTrivial() {
		assertTrue(true);
	}

}