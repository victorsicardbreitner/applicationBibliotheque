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
public class TestLivreDao {

	Logger logger = LoggerFactory.getLogger(TestLivreDao.class);
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
	public void testLivreEtEmpruntFetchEtLazy() {

		Livre livre1 = serviceLivre.sauvegarder(new Livre(null, "Harry Potter 1", "JKR", true));
		Lecteur lecteur1 = serviceLecteur.sauvegarder(new Lecteur("Paul", "NomPaul"));
		Lecteur lecteur2 = serviceLecteur.sauvegarder(new Lecteur("Jean", "NomJean"));

		Emprunt emprunt1 = new Emprunt(livre1, lecteur1);
		emprunt1 = serviceEmprunt.sauvegarder(emprunt1);
		
		Emprunt emprunt2 = new Emprunt(livre1, lecteur2);
		emprunt2 = serviceEmprunt.sauvegarder(emprunt2);

		Livre livre1FetchEmprunts = serviceLivre.trouverParIdFetchEmprunts(livre1.getId());

		Livre livre1ReluSansFetch = serviceLivre.trouverParId(livre1.getId());

		assertEquals(livre1FetchEmprunts.getEmprunts().size(),2);
		
		
		for (Emprunt empr : livre1FetchEmprunts.getEmprunts()) {
			assertEquals(empr.getLivre().getId(),livre1.getId());
		}
		
		
		// on ne peut pas aller chercher le get car le lien @---ToMany est LAZY
		boolean testNecessiteFetch=false;
		try {
			assertEquals(livre1ReluSansFetch.getEmprunts().size(),2);
		} catch (Exception e) {
			testNecessiteFetch=true;
			assertTrue(e instanceof org.hibernate.LazyInitializationException);
		}
		assertTrue(testNecessiteFetch);
		

	}

	@Test
	public void testTrivial() {
		assertTrue(true);
	}

}