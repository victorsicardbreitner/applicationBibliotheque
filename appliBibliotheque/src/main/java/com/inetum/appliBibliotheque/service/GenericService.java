package com.inetum.appliBibliotheque.service;

import java.util.List;

import com.inetum.appliBibliotheque.exception.NotFoundException;

public interface GenericService<E,ID,DTO> {
	public E trouverParId(ID id);
	public DTO trouverDtoParId(ID id) throws NotFoundException;
	
	public E sauvegarder(E e);
	public void suppressionParId(ID id) throws NotFoundException ;
	public Boolean existerParId(ID id);
	public List<E> trouverTout();
	public List<DTO> trouverToutDto();
	E sauvegarderParDto(DTO dto);
	DTO sauvegarderParDtoPourDto(DTO dto);
}
