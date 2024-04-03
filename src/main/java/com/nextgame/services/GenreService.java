package com.nextgame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.nextgame.entities.Genre;
import com.nextgame.repositories.IGenreRespository;

/**
 * Cette classe contient les services à utiliser par le controlleur
 */
@Service
public class GenreService {
	
	@Autowired
	IGenreRespository genreRespository;
	
	/** 
	 * Retourne une liste contenant tous les genres
	 * @return List<Genre>
	 */
	public List<Genre> getAll() {
		List<Genre> genres = new ArrayList<Genre>();
		genres.addAll(genreRespository.findAll());
		return genres;
	}
	
	/**
	 * Retourne un objet genre en fonction de l'id passé en paramètre
	 * @param id
	 * @return Genre
	 */
	public Genre getById(@PathVariable Long id) {
		return genreRespository.getReferenceById(id);
	}
	
	/**
	 * Permet d'enregistrer un genre et le retourne
	 * @param genre
	 * @return Genre
	 */
	public Genre save (Genre genre) {
		return genreRespository.save(genre);
	}
	
	/**
	 * Met à jour le genre passé dans le body
	 * @param genre
	 * @return Genre
	 */
	public Genre update (Genre genre) {
		return genreRespository.saveAndFlush(genre);
	}
	
	/**
	 * Supprime l'élément qui correpond à l'id passé en paramettre
	 * @param id du genre
	 */
	public void delete (Long id) {
		genreRespository.deleteById(id);
	}
}