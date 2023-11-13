package com.inetum.appliBibliotheque.service;

import com.inetum.appliBibliotheque.dto.DomaineDto;
import com.inetum.appliBibliotheque.entity.Domaine;

public interface ServiceDomaine extends GenericService<Domaine,Long,DomaineDto> {

	Domaine trouverParIdFetchLivres(Long id);

}
