package com.nextgame.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextgame.dtos.FranchiseDTO;
import com.nextgame.entities.Franchise;

public class FranchiseMapperImpl implements Mapper<Franchise, FranchiseDTO>{

	@Autowired
	private ModelMapper modelMapper;
	
	public FranchiseMapperImpl() {
		super();
	}
	
	@Override
	public FranchiseDTO mapToDto(Franchise franchise) {
		return modelMapper.map(franchise, FranchiseDTO.class);
	}

	@Override
	public Franchise mapToEntity(FranchiseDTO franchiseDto) {
		return  modelMapper.map(franchiseDto, Franchise.class);
	}
}