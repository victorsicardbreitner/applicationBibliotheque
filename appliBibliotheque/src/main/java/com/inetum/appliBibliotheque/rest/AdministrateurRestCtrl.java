package com.inetum.appliBibliotheque.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inetum.appliBibliotheque.dto.AdministrateurDto;
import com.inetum.appliBibliotheque.entity.Administrateur;
import com.inetum.appliBibliotheque.exception.NotFoundException;
import com.inetum.appliBibliotheque.service.ServiceAdmin;



@RestController
@RequestMapping(value="/api-bibli/administrateur" , headers="Accept=application/json")
@PreAuthorize("hasRole('ADMIN')")
public class AdministrateurRestCtrl {
	
	@Autowired
	private ServiceAdmin serviceAdmin;
	

	@GetMapping("/{idAdmin}")
	public ResponseEntity<?> getAdminById(@PathVariable("idAdmin") Long id) throws NotFoundException {
		Administrateur admin = serviceAdmin.trouverParId(id);
		if(admin!=null) {
			return new ResponseEntity<Administrateur>(admin, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String> ("{ \"err\" : \"admin not found\"}", HttpStatus.NOT_FOUND) ;
		}
	}
	
	@GetMapping("")
	public List<Administrateur> getAdmin(){
			return serviceAdmin.trouverTout();
	}
	
	
	//ex de fin URL: ./api-bibli/administrateur
	// appelé en mode POST avec dans la partie invisible "body" de la requête
	@PostMapping("" )
	public Administrateur postAdmin(@RequestBody Administrateur nouveauAdmin) {
		System.out.println("nouveauAdmin "+ nouveauAdmin);
		Administrateur adminEnregistreEnBase = serviceAdmin.sauvegarder(nouveauAdmin);
		return adminEnregistreEnBase; // on retourne le lecteur avec la clé primaire auto-incrémentée
	}
	
	
	//exemple de fin d'URL: ./api-bank/compte/1
	@DeleteMapping("/{idAdmin}" )
	public ResponseEntity<?> deleteAdminByNumero(@PathVariable("idAdmin") Long id) {
	    Administrateur adminAsupprimer = serviceAdmin.trouverParId(id);
	    if(adminAsupprimer == null) 
	    	   		 return new ResponseEntity<String>("{ \"err\" : \"admin not found\"}" ,
	 			           HttpStatus.NOT_FOUND);//40
	    serviceAdmin.suppressionParId(id);
	    return new ResponseEntity<>("{ \"done\" : \"admin deleted\"}" ,
	    	   HttpStatus.OK);
	    // ou bien
	   // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    		    
	}
	
	
	
}
