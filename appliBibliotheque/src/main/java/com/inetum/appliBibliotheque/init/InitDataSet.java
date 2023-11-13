package com.inetum.appliBibliotheque.init;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.inetum.appliBibliotheque.dao.DaoAdmin;
import com.inetum.appliBibliotheque.dao.DaoDomaine;
import com.inetum.appliBibliotheque.dao.DaoEmprunt;
import com.inetum.appliBibliotheque.dao.DaoIncident;
import com.inetum.appliBibliotheque.dao.DaoLecteur;
import com.inetum.appliBibliotheque.dao.DaoLivre;
import com.inetum.appliBibliotheque.dao.security.DaoAuthorities;
import com.inetum.appliBibliotheque.dao.security.DaoUsers;
import com.inetum.appliBibliotheque.entity.Administrateur;
import com.inetum.appliBibliotheque.entity.Domaine;
import com.inetum.appliBibliotheque.entity.Emprunt;
import com.inetum.appliBibliotheque.entity.Incident;
import com.inetum.appliBibliotheque.entity.Lecteur;
import com.inetum.appliBibliotheque.entity.Livre;
import com.inetum.appliBibliotheque.security.Authorities;
import com.inetum.appliBibliotheque.security.Users;
import com.inetum.appliBibliotheque.utils.AppUtils;

/**
 * Classe utilitaire qui initalise un jeu de données au démarrage de l'application.
 * @author SRV
 *
 */
@Component

@Profile("init")
public class InitDataSet {
	
	@Autowired
	private DaoLivre daoLivreJpa;
	
	@Autowired
	private DaoLecteur daoLecteurJpa;
	
	@Autowired
	private DaoAdmin daoAdminJpa;
	
	@Autowired
	private DaoEmprunt daoEmpruntJpa;
	
	@Autowired
	private DaoIncident daoIncidentJpa;

	@Autowired
	private DaoDomaine daoDomaineJpa;
	
	@Autowired
	private DaoUsers daoUsers;
	
	@Autowired
	private DaoAuthorities daoAuthorities;
	
	@PostConstruct
	public void initData() { // pour que les tables de soient pas vide
		
		
		
		Logger logger = LoggerFactory.getLogger(InitDataSet.class);
		
		
		Domaine domaine1= new Domaine(null,"livre de biologie","science");
		daoDomaineJpa.save(domaine1);
		
		Domaine domFantasy= new Domaine("Fantasy",
				"La fantasy, terme issu de l'anglais fantasy est un genre artistique et littéraire qui représente des phénomènes surnaturels imaginaires, souvent associés au mythe et souvent figurés par l'intervention ou l'emploi de la magie et de l'anachronisme.");
		daoDomaineJpa.save(domFantasy);
		
		
		

    	Livre livre1 = daoLivreJpa.save(new Livre(null,"Harry Potter 1" , "JKR",true,domFantasy));
    	Livre livre2 = daoLivreJpa.save(new Livre(null,"Harry Potter 2" , "JKR",true,domFantasy));
    	daoLivreJpa.save(new Livre(null,"Harry Potter 3" , "JKR",true,domFantasy));
    	daoLivreJpa.save(new Livre(null,"Harry Potter 4" , "JKR",true,domFantasy));
    	daoLivreJpa.save(new Livre(null,"Harry Potter 5" , "JKR",true,domFantasy));
    	daoLivreJpa.save(new Livre(null,"Harry Potter 6" , "JKR",true,domFantasy));
    	daoLivreJpa.save(new Livre(null,"Harry Potter 7" , "JKR",false,domFantasy));
    	daoLivreJpa.save(new Livre(null,"Le Seigneur des Anneaux" , "Tolkien",true,domFantasy));
    	daoLivreJpa.save(new Livre(null,"Les Miserables" , "Victor Hugo",true));
    	daoLivreJpa.save(new Livre(null,"Madame Bovary" , "Gustave Flaubert",true));
    	daoAdminJpa.save(new Administrateur(null, "Soulef", "Saoud", "soulefsaoud@biblio.fr", "06XXXXXXXX", "5", "rue de la Biologie",
    			"Paris", "75012", "France", "SoulefS", "Eucaryote"));
		daoAdminJpa.save(new Administrateur(null,"Victor", "Sicard", "victor.sicard@biblio.fr", "06XXXXXXXX",
				"8", "rue des Mathématiques", "Paris", "75012", "France", "VictorS", "Cauchy-Schwartz"));
		daoAdminJpa.save(new Administrateur(null, "Roland", "Panzou", "roland.panzou@biblio.fr", "06XXXXXXXX",
				"3", "rue de la Chimie", "Paris", "75012", "France", "RolandP", "Helium"));
		
		
		Lecteur lecteur1 = daoLecteurJpa.save(new Lecteur("Paul" , "Dirac"));
		Emprunt emprunt1 = daoEmpruntJpa.save(new Emprunt(livre1,lecteur1));
		emprunt1.setDateDebut(AppUtils.ajouterJours(emprunt1.getDateDebut(), -20));
		emprunt1.setDateFin(AppUtils.ajouterJours(emprunt1.getDateFin(), -20));
		daoEmpruntJpa.save(emprunt1);
		
		//logger.debug("EMPRUNT : "+ emprunt1.getId().toString());
		

		


		Incident incident1 = new Incident("motif 1");
		incident1.setEmprunt(emprunt1);
		daoIncidentJpa.save(incident1);
		
		Lecteur lecteur2 = daoLecteurJpa.save(new Lecteur("Joseph" , "Staline"));
		//logger.debug("EMPRUNT : "+ daoLecteurJpa.findById(lecteur2.getId()));
		
		
		
		/*
		Emprunt emprunt2 = new Emprunt(livre2,lecteur2);
		logger.debug("EMPRUNT2 : "+ emprunt2.getId().toString());
		daoEmpruntJpa.insert(emprunt2);
		*/
		
		daoUsers.save(new Users("user1","$2a$10$yl7N17Pooyc/HSy7t1rsYe4I2BVQnv8.woLrYeBPL.JnfXMCXBCve", "Y"));
		daoAuthorities.save(new Authorities("user1","ROLE_USER"));
		
		daoUsers.save(new Users("admin1","$2a$10$j1K7Yx1ZqbZMYPgUArjxR.z6qYhfRmQ94rh0J8GZZRsEtGYYQSicq", "Y"));
		daoAuthorities.save(new Authorities("admin1","ROLE_ADMIN"));
		

		
	}
	
	//public void initDataAdmin() {

	//}
}
