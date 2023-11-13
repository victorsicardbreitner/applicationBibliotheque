package com.inetum.appliBibliotheque.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inetum.appliBibliotheque.dto.EmpruntDto;
import com.inetum.appliBibliotheque.entity.Emprunt;
import com.inetum.appliBibliotheque.entity.EmpruntCompositePk;
import com.inetum.appliBibliotheque.entity.Incident;
import com.inetum.appliBibliotheque.entity.Lecteur;
import com.inetum.appliBibliotheque.entity.Livre;
import com.inetum.appliBibliotheque.exception.LivreIndisponibleException;
import com.inetum.appliBibliotheque.exception.NotFoundException;
import com.inetum.appliBibliotheque.init.InitDataSet;
import com.inetum.appliBibliotheque.service.ServiceEmprunt;
import com.inetum.appliBibliotheque.service.ServiceIncident;
import com.inetum.appliBibliotheque.service.ServiceLecteur;
import com.inetum.appliBibliotheque.service.ServiceLivre;


@RestController
@RequestMapping(value="/api-bibli/emprunt" , headers="Accept=application/json")
//ATTENTION origins = "*" peut être un problème de sécurité
//@PreAuthorize("hasRole('ADMIN')") //ne permet pas de preciser quelle methode (POST/GEt etc.) est autorisee ou non, on paramètre dans WebSecurityRecentConfig
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST}) //pour autoriser les appels extérieurs  Cross-origin resource sharing
public class EmpruntRestCtrl {
	
	Logger logger = LoggerFactory.getLogger(InitDataSet.class);
	
	@Autowired
	private ServiceEmprunt serviceEmprunt;
	
	@Autowired
	private ServiceLivre serviceLivre;
	
	@Autowired
	private ServiceLecteur serviceLecteur;
	
	@Autowired
	private ServiceIncident serviceIncident;
	
	/*
	@GetMapping("/{idEmprunt}")
	public ResponseEntity<?> getCompteByNumero(@PathVariable("idEmprunt") EmpruntCompositePk id) {
		Emprunt emprunt = daoEmpruntJpa.findById(id);
		if(emprunt!=null) {
			return new ResponseEntity<Emprunt>(emprunt, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String> ("{ \"err\" : \"emprunt non trouvé\"}", HttpStatus.NOT_FOUND) ;
		}
	}
	*/
	
	@GetMapping("")
	public List<EmpruntDto> getEmprunt(){
			return serviceEmprunt.trouverToutDto();
	}
	
	@GetMapping("/{idLecteur}")
	public List<EmpruntDto> getEmprunt(@PathVariable("idLecteur") Long idLecteur){
			Lecteur lecteur = serviceLecteur.trouverParId(idLecteur);
			return serviceEmprunt.trouverParLecteurDto(lecteur);
	}
	
	
	//ex de fin URL: ./api-bibli/emprunt?idLivre=2&idLecteur=4
	// appelé en mode POST avec dans la partie invisible "body" de la requête
	@PostMapping("")
	public ResponseEntity<?> postEmprunt(@RequestParam(value="idLivre",required=false) Long idLivre, @RequestParam(value="idLecteur",required=false) Long idLecteur, @RequestParam(value="motifIncident",required=false) String motifIncident) throws LivreIndisponibleException {
		

		Livre livre = serviceLivre.trouverParId(idLivre);
		Lecteur lecteur = serviceLecteur.trouverParId(idLecteur);
		Incident incident = null;
	    if(!motifIncident.isEmpty()) {
		   incident = new Incident(motifIncident);
		   serviceIncident.sauvegarder(incident);
	    }
		
		
		logger.debug("Livre : "+ livre.getTitre());
		logger.debug("Lecteur : "+ lecteur.getNom());
		
		
	    
		
		if(livre.getDispo()) {
			livre.setDispo(false);
			serviceLivre.sauvegarder(livre);
			Emprunt nouvelEmprunt = new Emprunt(livre,lecteur);
			nouvelEmprunt.setIncident(incident);
			Emprunt EmpruntEnregistreEnBase = serviceEmprunt.sauvegarder(nouvelEmprunt);
			return new ResponseEntity<Emprunt>(EmpruntEnregistreEnBase , HttpStatus.OK);
		}
		else {
			throw new LivreIndisponibleException();
		}
	}
	
	
	//exemple de fin d'URL:  ./api-bibli/emprunt?idLivre=2&idLecteur=1
	//?idLivre={idLivre}&idLecteur={idLecteur}
	@DeleteMapping("")
	public ResponseEntity<?> deleteEmprunt(@RequestParam(value="idLivre",required=true) Long idLivre, @RequestParam(value="idLecteur",required=true) Long idLecteur) throws NotFoundException {
		EmpruntCompositePk idEmprunt=new EmpruntCompositePk(idLivre,idLecteur);
	    Emprunt EmpruntAsupprimer = serviceEmprunt.trouverParId(idEmprunt);
	    System.out.println("Emprunt supprimer"+ EmpruntAsupprimer);
	    if(EmpruntAsupprimer == null) throw new NotFoundException();
	    	   		 //return new ResponseEntity<String>("{ \"err\" : \"Emprunt not found\"}" , HttpStatus.NOT_FOUND);//40
	    	
	    serviceEmprunt.suppressionParId(idEmprunt);
	    Livre livre = serviceLivre.trouverParId(idLivre);
	    livre.setDispo(true);
		serviceLivre.sauvegarder(livre);
	    return new ResponseEntity<>("{ \"done\" : \"Emprunt deleted\"}" ,
	    	   HttpStatus.OK);
	    // ou bien
	   // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    		    
	}
	
	@PutMapping("")
	public  ResponseEntity<?> updateEmprunt(@RequestParam(value="idLivre",required=false) Long idLivre, @RequestParam(value="idLecteur",required=false) Long idLecteur, @RequestParam(value="motifIncident",required=false) String motifIncident) throws NotFoundException {
		EmpruntCompositePk idEmprunt=new EmpruntCompositePk(idLivre,idLecteur);
		Emprunt EmpruntQuiDevraitExister = serviceEmprunt.trouverParId(idEmprunt);
	
	    if(EmpruntQuiDevraitExister==null) throw new NotFoundException();
	    
	    Incident incident = null;
	    if(!motifIncident.isEmpty()) {
		   incident = new Incident(motifIncident);
		   
		   incident = serviceIncident.sauvegarder(incident);
		   logger.debug("########### incident id : "+incident.getId());
	    }
	    
	    EmpruntQuiDevraitExister.setIncident(incident);
	    
	    serviceEmprunt.sauvegarder(EmpruntQuiDevraitExister);
		return new ResponseEntity<Emprunt>(EmpruntQuiDevraitExister , HttpStatus.OK);
    }

		
}
