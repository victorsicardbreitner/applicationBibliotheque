package com.inetum.appliBibliotheque.converter;

import org.springframework.beans.BeanUtils;

import com.inetum.appliBibliotheque.dto.EmpruntDto;
import com.inetum.appliBibliotheque.entity.Emprunt;

public class EmpruntConverter extends GenericConverter<Emprunt,EmpruntDto> {

	@Override
	public /*static*/ EmpruntDto map(Emprunt source, Class<EmpruntDto> targetClass) {
		EmpruntDto target = new EmpruntDto();
		try {
			EmpruntDto empDto = new EmpruntDto(
						source.getLecteur().getPrenom(),
						source.getLivre().getTitre(),
						source.getDateDebut().toString(),
						source.getDateFin().toString(),
						null, //on definit l'incident après
						source.getLecteur().getId(),
						source.getLivre().getId()
						);
			if(source.getIncident() != null) empDto.setIncident(source.getIncident().getMotif());
			else empDto.setIncident("aucun incident");
			BeanUtils.copyProperties(empDto, target);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;
	}

}
