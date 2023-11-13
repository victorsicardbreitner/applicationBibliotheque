package com.inetum.appliBibliotheque.converter;

import org.springframework.beans.BeanUtils;

import com.inetum.appliBibliotheque.dto.LivreDto;
import com.inetum.appliBibliotheque.entity.Livre;

public class LivreConverter extends GenericConverter<Livre,LivreDto> {

	@Override
	public /*static*/ LivreDto map(Livre source, Class<LivreDto> targetClass) {
		LivreDto target = new LivreDto();
		try {
			LivreDto livDto = new LivreDto(
						source.getId(),
						source.getTitre().toUpperCase(),
						source.getAuteur(),
						source.getDispo(),
						source.getEtat().toString(),
						""
						);
			if(source.getDomaine() != null) livDto.setDomaine(source.getDomaine().getNom());
			else livDto.setDomaine("aucun domaine");
			BeanUtils.copyProperties(livDto, target);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;
	}

}
