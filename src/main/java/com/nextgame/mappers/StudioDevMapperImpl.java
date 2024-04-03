package com.nextgame.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextgame.dtos.StudioDevDTO;
import com.nextgame.entities.StudioDev;

public class StudioDevMapperImpl implements Mapper<StudioDev, StudioDevDTO>{

	@Autowired
	private ModelMapper modelMapper;
	
	public StudioDevMapperImpl() {
		super();
	}
	
	@Override
	public StudioDevDTO mapToDto(StudioDev studioDev) {
		return modelMapper.map(studioDev, StudioDevDTO.class);
	}

	@Override
	public StudioDev mapToEntity(StudioDevDTO studioDevDTO) {
		return  modelMapper.map(studioDevDTO, StudioDev.class);
	}

}
