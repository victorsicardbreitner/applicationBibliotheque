package com.inetum.appliBibliotheque.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.inetum.appliBibliotheque.converter.Converter;
import com.inetum.appliBibliotheque.converter.GenericConverter;
import com.inetum.appliBibliotheque.exception.NotFoundException;

public abstract class AbstractGenericService<E,ID,DTO,CONV extends GenericConverter<E,DTO>> implements GenericService<E,ID,DTO> {
	
	//private CrudRepository<E,ID> dao; 
	
	public abstract CrudRepository<E,ID> getDao();
	public abstract Class<DTO> getDtoClass();
	public abstract Class<E> getEClass();
	public abstract CONV getCONV();
	


	@Override
	public E trouverParId(ID id) {
		return getDao().findById(id).orElse(null);
	}

	@Override
	public E sauvegarder(E e) {
		return getDao().save(e);
	}

	@Override
	public void suppressionParId(ID id) throws NotFoundException{
		if(!getDao().existsById(id)) throw new NotFoundException("no entity to delete for id="+id);
		getDao().deleteById(id);
	}

	@Override
	public Boolean existerParId(ID id) {
		return getDao().existsById(id);
	}

	@Override
	public List<E> trouverTout() {
		return (List<E>) getDao().findAll();
	}
	
	
	//DTO :
	
	
	/*
	@Override
	public DTO trouverDtoParId(ID id) {
		if(this.trouverParId(id)==null) return null;
		return GenericConverter.map(this.trouverParId(id), getDtoClass()); //ex : dtoClass=CompteDto.class
	};
	*/
	
	@Override
	public DTO trouverDtoParId(ID id) throws NotFoundException {
		E e = this.trouverParId(id);
		if(e!=null)  return getCONV().map(this.trouverParId(id), getDtoClass()); //ex : dtoClass=CompteDto.class
		else throw new NotFoundException("entity not found for id="+id);
	};
	
	public List<DTO> trouverToutDto(){
		return getCONV().map(this.trouverTout(), getDtoClass());
	};
	
	@Override
	public E sauvegarderParDto(DTO dto) {
		E e = getCONV().mapRetour(dto, getEClass());
		return this.sauvegarder(e);
	}
	
	@Override
	public DTO sauvegarderParDtoPourDto(DTO dto) {
		return getCONV().map(sauvegarderParDto(dto), getDtoClass());
	}


}
