package com.nextgame.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextgame.dtos.DeveloppeurDTO;
import com.nextgame.entities.Developpeur;

public class DeveloppeurMapperImpl implements Mapper<Developpeur, DeveloppeurDTO>{

	@Autowired
	private ModelMapper modelMapper;
	
	public DeveloppeurMapperImpl() {
		super();
	}
	
	@Override
	public DeveloppeurDTO mapToDto(Developpeur developpeur) {
		return modelMapper.map(developpeur, DeveloppeurDTO.class);
	}

	@Override
	public Developpeur mapToEntity(DeveloppeurDTO developpeurDto) {
		return  modelMapper.map(developpeurDto, Developpeur.class);
	}
}