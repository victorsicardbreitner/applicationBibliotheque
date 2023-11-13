package com.inetum.appliBibliotheque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inetum.appliBibliotheque.converter.GenericConverter;
import com.inetum.appliBibliotheque.converter.LivreConverter;
import com.inetum.appliBibliotheque.dao.DaoLivre;
import com.inetum.appliBibliotheque.dto.LivreDto;
import com.inetum.appliBibliotheque.entity.Emprunt;
import com.inetum.appliBibliotheque.entity.Lecteur;
import com.inetum.appliBibliotheque.entity.Livre;

@Service
@Transactional 
public class ServiceLivreImpl extends AbstractGenericService<Livre,Long,LivreDto,LivreConverter> implements ServiceLivre{
	
	@Autowired
	private DaoLivre daoLivre;
	
	@Autowired
	private ServiceEmprunt serviceEmprunt;
	
	@Override
	public CrudRepository<Livre,Long> getDao() {
		return this.daoLivre;
	}
	
	@Override
	public Class<LivreDto> getDtoClass() {
		return LivreDto.class;
	}
	
	@Override
	public Class<Livre> getEClass() {
		return Livre.class;
	}
	
	@Override
	public LivreConverter getCONV() {
		return new LivreConverter();
	}


	

	@Override
	public List<Livre> trouverLivreParLecteur(Lecteur lecteur) {
		List<Emprunt> listeEmpruntsLecteur = serviceEmprunt.trouverParLecteur(lecteur);
		return listeEmpruntsLecteur.stream()
						.map(emprunt -> emprunt.getLivre())
						.toList();
	}
	
	
	@Override
	public List<Livre> trouverLivreActuelParLecteur(Lecteur lecteur) {
		List<Emprunt> listeEmpruntsLecteurActuel = serviceEmprunt.trouverParLecteurEtDateFinApresMaintenant(lecteur);
		return listeEmpruntsLecteurActuel.stream()
						.map(emprunt -> emprunt.getLivre())
						.toList();
	}

	@Override
	public List<Livre> trouverParTitre(String titre) {
		return daoLivre.findByTitre(titre);
	}
	
	@Override
	public List<LivreDto> trouverDtoParTitre(String titre) {
		return getCONV().map(daoLivre.findByTitre(titre), getDtoClass());
	}

	@Override
	public Livre trouverParIdFetchEmprunts(Long id) {
		return daoLivre.findByIdFetchEmprunts(id);
	}
	
	@Override
	public LivreDto trouverDtoParIdFetchEmprunts(Long id) {
		return getCONV().map(daoLivre.findByIdFetchEmprunts(id), getDtoClass());
	}


	
	
	
	
	//
	

}
