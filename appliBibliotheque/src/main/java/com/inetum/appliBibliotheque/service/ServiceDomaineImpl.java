package com.inetum.appliBibliotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inetum.appliBibliotheque.converter.GenericConverter;
import com.inetum.appliBibliotheque.dao.DaoDomaine;
import com.inetum.appliBibliotheque.dto.DomaineDto;
import com.inetum.appliBibliotheque.entity.Domaine;

@Service
@Transactional 
public class ServiceDomaineImpl extends AbstractGenericService<Domaine,Long,DomaineDto,GenericConverter<Domaine,DomaineDto>> implements ServiceDomaine{

	@Autowired
	private DaoDomaine daoDomaine;
	
	@Override
	public CrudRepository<Domaine, Long> getDao() {
		return this.daoDomaine;
	}

	@Override
	public Class<DomaineDto> getDtoClass() {
		return DomaineDto.class;
	}

	@Override
	public Class<Domaine> getEClass() {
		return Domaine.class;
	}

	@Override
	public GenericConverter<Domaine, DomaineDto> getCONV() {
		return new GenericConverter<Domaine, DomaineDto>();
	}
	
	
	@Override
	public Domaine trouverParIdFetchLivres(Long id) {
		return daoDomaine.findByIdFetchLivres(id);
	}


}
