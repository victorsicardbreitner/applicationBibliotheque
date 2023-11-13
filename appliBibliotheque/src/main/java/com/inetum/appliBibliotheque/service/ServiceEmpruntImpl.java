package com.inetum.appliBibliotheque.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inetum.appliBibliotheque.converter.EmpruntConverter;
import com.inetum.appliBibliotheque.dao.DaoEmprunt;
import com.inetum.appliBibliotheque.dto.EmpruntDto;
import com.inetum.appliBibliotheque.entity.Emprunt;
import com.inetum.appliBibliotheque.entity.EmpruntCompositePk;
import com.inetum.appliBibliotheque.entity.Lecteur;
import com.inetum.appliBibliotheque.utils.AppUtils;

@Service
@Transactional 
public class ServiceEmpruntImpl extends AbstractGenericService<Emprunt,EmpruntCompositePk,EmpruntDto,EmpruntConverter> implements ServiceEmprunt {

	@Autowired
	private DaoEmprunt daoEmprunt;
	
	@Override
	public CrudRepository<Emprunt,EmpruntCompositePk> getDao() {
		return this.daoEmprunt;
	}
	
	@Override
	public Class<EmpruntDto> getDtoClass() {
		return EmpruntDto.class;
	}
	
	@Override
	public Class<Emprunt> getEClass() {
		return Emprunt.class;
	}
	
	@Override
	public EmpruntConverter getCONV() {
		return new EmpruntConverter();
	}



	
	
	
	
	@Override
	public List<Emprunt> trouverParLecteur(Lecteur lecteur) {
		return daoEmprunt.findByLecteur(lecteur);
	}

	@Override
	public List<EmpruntDto> trouverParLecteurDto(Lecteur lecteur) {
		return getCONV().map(daoEmprunt.findByLecteur(lecteur), getDtoClass());
	}

	@Override
	public List<Emprunt> trouverParLecteurEtDateFinApres(Lecteur lecteur,Date date) {
		return daoEmprunt.findByLecteurAndDateFinAfter(lecteur,date);
	}

	@Override
	public List<Emprunt> trouverParLecteurEtDateFinApresMaintenant(Lecteur lecteur) {
		return this.trouverParLecteurEtDateFinApres(lecteur,AppUtils.asDate(LocalDate.now()));
	}



}
