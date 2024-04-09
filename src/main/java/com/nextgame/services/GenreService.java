package com.nextgame.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.nextgame.entities.Genre;
import com.nextgame.repositories.IGenreRespository;

/**
 * Cette classe contient tous les appels CRUD du repository Genre
 */
@Service
public class GenreService implements IService<Genre, Long>{
	
	@Autowired
	IGenreRespository genreRespository;
	
	/** 
	 * Retourne une liste de tous les genres
	 * @return List<Genre>
	 */
	@Override
	public List<Genre> getAll() {
		return genreRespository.findAll();
	}
	
	/**
	 * Retourne un objet genre en fonction de l'id passé en paramètre
	 * @param id
	 * @return Genre
	 */
	@Override
	public Genre getById(@PathVariable Long id) {
		return genreRespository.getReferenceById(id);
	}
	
	/**
	 * Permet d'enregistrer un genre et le retourne
	 * @param genre
	 * @return Genre
	 */
	@Override
	public Genre save (Genre genre) {
		return genreRespository.save(genre);
	}
	
	/**
	 * Met à jour le genre passé dans le body
	 * @param genre
	 * @return Genre
	 */
	@Override
	public Genre update (Genre genre) {
		return genreRespository.saveAndFlush(genre);
	}
	
	/**
	 * Supprime l'élément correpondant à l'id passé en paramètre
	 * @param id du genre
	 */
	@Override
	public void delete (Long id) {
		genreRespository.deleteById(id);
	}
}