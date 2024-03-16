package com.nextgame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgame.entities.Genre;
import com.nextgame.repositories.IGenreRespository;

@Service
public class GenreService {
	
	@Autowired
	IGenreRespository genreRespository;
	
	public List<Genre> getAll() {
		List<Genre> genres = new ArrayList<Genre>();
		genres.addAll(genreRespository.findAll());
		return genres;
	}
}