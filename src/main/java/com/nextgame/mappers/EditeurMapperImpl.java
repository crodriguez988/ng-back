package com.nextgame.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextgame.dtos.EditeurDTO;
import com.nextgame.entities.Editeur;

public class EditeurMapperImpl implements Mapper<Editeur, EditeurDTO>{

	@Autowired
	private ModelMapper modelMapper;
	
	public EditeurMapperImpl() {
		super();
	}

	@Override
	public EditeurDTO mapToDto(Editeur editeur) {
		return modelMapper.map(editeur, EditeurDTO.class);
	}

	@Override
	public Editeur mapToEntity(EditeurDTO editeurDTO) {
		return modelMapper.map(editeurDTO, Editeur.class);
	}
}