package com.inetum.appliBibliotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inetum.appliBibliotheque.converter.GenericConverter;
import com.inetum.appliBibliotheque.dao.DaoAdmin;
import com.inetum.appliBibliotheque.dto.AdministrateurDto;
import com.inetum.appliBibliotheque.entity.Administrateur;

@Service
@Transactional 
public class ServiceAdminImpl extends AbstractGenericService<Administrateur,Long,AdministrateurDto,GenericConverter<Administrateur,AdministrateurDto>> implements ServiceAdmin{

	@Autowired
	private DaoAdmin daoAdmin;
	
	@Override
	public CrudRepository<Administrateur, Long> getDao() {
		return this.daoAdmin;
	}

	@Override
	public Class<AdministrateurDto> getDtoClass() {
		return AdministrateurDto.class;
	}

	@Override
	public Class<Administrateur> getEClass() {
		return Administrateur.class;
	}

	@Override
	public GenericConverter<Administrateur, AdministrateurDto> getCONV() {
		return new GenericConverter<Administrateur, AdministrateurDto>();
	}


}
