package com.inetum.appliBibliotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inetum.appliBibliotheque.converter.GenericConverter;
import com.inetum.appliBibliotheque.dao.DaoLecteur;
import com.inetum.appliBibliotheque.dto.LecteurDto;
import com.inetum.appliBibliotheque.entity.Lecteur;

@Service
@Transactional 
public class ServiceLecteurImpl  extends AbstractGenericService<Lecteur,Long,LecteurDto,GenericConverter<Lecteur,LecteurDto>> implements ServiceLecteur {

	@Autowired
	private DaoLecteur daoLecteur;
	
	@Override
	public CrudRepository<Lecteur, Long> getDao() {
		return this.daoLecteur;
	}

	@Override
	public Class<LecteurDto> getDtoClass() {
		return LecteurDto.class;
	}

	@Override
	public Class<Lecteur> getEClass() {
		return Lecteur.class;
	}

	@Override
	public GenericConverter<Lecteur, LecteurDto> getCONV() {
		return new GenericConverter<Lecteur, LecteurDto>();
	}



}
