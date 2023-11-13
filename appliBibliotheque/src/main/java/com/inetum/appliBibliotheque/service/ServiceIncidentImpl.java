package com.inetum.appliBibliotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inetum.appliBibliotheque.converter.GenericConverter;
import com.inetum.appliBibliotheque.dao.DaoIncident;
import com.inetum.appliBibliotheque.dto.IncidentDto;
import com.inetum.appliBibliotheque.entity.Incident;

@Service
@Transactional 
public class ServiceIncidentImpl extends AbstractGenericService<Incident,Long,IncidentDto,GenericConverter<Incident,IncidentDto>> implements ServiceIncident {

	@Autowired
	private DaoIncident daoIncident;
	
	@Override
	public CrudRepository<Incident, Long> getDao() {
		return this.daoIncident;
	}

	@Override
	public Class<IncidentDto> getDtoClass() {
		return IncidentDto.class;
	}

	@Override
	public Class<Incident> getEClass() {
		return Incident.class;
	}

	@Override
	public GenericConverter<Incident, IncidentDto> getCONV() {
		return new GenericConverter<Incident, IncidentDto>();
	}



}
