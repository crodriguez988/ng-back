package com.nextgame.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextgame.dtos.PlateformeDTO;
import com.nextgame.entities.Plateforme;

public class PlateformeMapperImpl implements Mapper<Plateforme, PlateformeDTO>{

	@Autowired
	private ModelMapper modelMapper;
	
	public PlateformeMapperImpl() {
		super();
	}
	
	@Override
	public PlateformeDTO mapToDto(Plateforme plateforme) {
		return modelMapper.map(plateforme, PlateformeDTO.class);
	}

	@Override
	public Plateforme mapToEntity(PlateformeDTO plateformeDTO) {
		return  modelMapper.map(plateformeDTO, Plateforme.class);
	}
}