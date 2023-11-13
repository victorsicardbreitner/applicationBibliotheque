package com.inetum.appliBibliotheque.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.inetum.appliBibliotheque.entity.Domaine;
import com.inetum.appliBibliotheque.entity.Livre;
import com.inetum.appliBibliotheque.service.ServiceDomaine;
import com.inetum.appliBibliotheque.service.ServiceLivre;

@SpringBootTest
@ActiveProfiles({"oracle"})
public class TestDomaineDao {

	Logger logger = LoggerFactory.getLogger(TestDomaineDao.class);
	@Autowired
	private ServiceLivre serviceLivre ;

	@Autowired
	private ServiceDomaine serviceDomaine;

	@Test
	public void testFind() {
		serviceLivre.trouverTout();
	}

	@Test
	public void testDomaineEtLivreFetchEtLazy() {

		
		Domaine domaine1 = serviceDomaine.sauvegarder(new Domaine("Biologie", "Etude du vivant"));

		Livre livre1 = new Livre(null, "Parasitisme, Ecologie et évolution des interactions durables"
				,"Claude Combes", true);
		livre1.setDomaine(domaine1);
		livre1 = serviceLivre.sauvegarder(livre1);
		
		Livre livre2 = new Livre(null, "Eléments de biologie à l'usage des autres disciplines" ,"Jacques Demongeot", true);
		livre2.setDomaine(domaine1);
		livre2 = serviceLivre.sauvegarder(livre2);

		Domaine domaine1FetchLivres = serviceDomaine.trouverParIdFetchLivres(domaine1.getId());

		Domaine domaine1ReluSansFetch = serviceDomaine.trouverParId(domaine1.getId()); /*.orElse(domaine1FetchLivres);*/

		assertEquals(domaine1FetchLivres.getLivres().size(),2);
		
		
		for (Livre liv : domaine1FetchLivres.getLivres()) {
			assertEquals(liv.getDomaine().getId(),domaine1.getId());
		}

		// on ne peut pas aller chercher le get car le lien @---ToMany est LAZY
		boolean testNecessiteFetch=false;
		try {
			assertEquals(domaine1ReluSansFetch.getLivres().size(),2);
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