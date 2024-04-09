package com.nextgame.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextgame.dtos.JeuDTO;
import com.nextgame.entities.Jeu;

public class JeuMapperImpl implements Mapper<Jeu, JeuDTO>{

	@Autowired
	private ModelMapper modelMapper;
	
	public JeuMapperImpl() {
		super();
	}
	
	@Override
	public JeuDTO mapToDto(Jeu jeu) {
		return modelMapper.map(jeu, JeuDTO.class);
	}

	@Override
	public Jeu mapToEntity(JeuDTO jeuDTO) {
		return modelMapper.map(jeuDTO, Jeu.class);
	}
}