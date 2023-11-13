package com.inetum.appliBibliotheque.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inetum.appliBibliotheque.entity.Lecteur;
import com.inetum.appliBibliotheque.exception.NotFoundException;
import com.inetum.appliBibliotheque.service.ServiceLecteur;



@RestController
@RequestMapping(value="/api-bibli/lecteur" , headers="Accept=application/json")
//ATTENTION origins = "*" peut être un problème de sécurité
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST}) //pour autoriser les appels extérieurs  Cross-origin resource sharing
public class LecteurRestCtrl {
	
	@Autowired
	private ServiceLecteur serviceLecteur;
	

	@GetMapping("/{idLecteur}")
	public ResponseEntity<?> getLecteurById(@PathVariable("idLecteur") Long id) throws NotFoundException {
		Lecteur lecteur = serviceLecteur.trouverParId(id);
		if(lecteur!=null) {
			return new ResponseEntity<Lecteur>(lecteur, HttpStatus.OK);
		}
		else {
			throw new NotFoundException();
		}
	}
	
	@GetMapping("")
	public List<Lecteur> getLecteurs(){
			return serviceLecteur.trouverTout();
	}
	
	
	//ex de fin URL: ./api-bibli/lecteur
	// appelé en mode POST avec dans la partie invisible "body" de la requête
	@PostMapping("" )
	public Lecteur postLecteur(@RequestBody Lecteur nouveauLecteur) {
		System.out.println("nouveauLecteur "+ nouveauLecteur);
		Lecteur lecteurEnregistreEnBase = serviceLecteur.sauvegarder(nouveauLecteur);
		return lecteurEnregistreEnBase; // on retourne le lecteur avec la clé primaire auto-incrémentée
	}
	
	
	//exemple de fin d'URL: ./api-bank/compte/1
	@DeleteMapping("/{idLecteur}" )
	public ResponseEntity<?> deleteLecteurByNumero(@PathVariable("idLecteur") Long id) throws NotFoundException{
	    Lecteur lecteurAsupprimer = serviceLecteur.trouverParId(id);
	    if(lecteurAsupprimer == null) throw new NotFoundException();
	    serviceLecteur.suppressionParId(id);
	    return new ResponseEntity<>("{ \"done\" : \"lecteur deleted\"}" ,
	    	   HttpStatus.OK);
	    // ou bien
	   // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    		    
	}
	
	
	
}
