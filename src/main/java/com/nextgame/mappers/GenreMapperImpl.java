package com.nextgame.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextgame.dtos.GenreDTO;
import com.nextgame.entities.Genre;

public class GenreMapperImpl implements Mapper<Genre, GenreDTO> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GenreMapperImpl() {
		super();
	}

	@Override
	public GenreDTO mapToDto(Genre genre) {
		return modelMapper.map(genre, GenreDTO.class);
	}

	@Override
	public Genre mapToEntity(GenreDTO genreDto) {
		return modelMapper.map(genreDto, Genre.class);
	}
}