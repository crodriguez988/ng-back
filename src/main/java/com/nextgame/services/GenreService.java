package com.nextgame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.nextgame.entities.Genre;
import com.nextgame.repositories.IGenreRespository;

@Service
public class GenreService {
	
	@Autowired
	IGenreRespository genreRespository;
	
	/** 
	 * Retourne une liste contenant tous les genres
	 * @return
	 */
	public List<Genre> getAll() {
		List<Genre> genres = new ArrayList<Genre>();
		genres.addAll(genreRespository.findAll());
		return genres;
	}
	
	public Genre getById(@PathVariable Long id) {
		return genreRespository.getReferenceById(id);
	}
	
	public Genre save (Genre genre) {
		return genreRespository.save(genre);
	}
	
	/**
	 * Supprime l'élément qui correpond à l'id passé en paramettre
	 * @param id du genre
	 */
	public void delete (Long id) {
		genreRespository.deleteById(id);
	}
}