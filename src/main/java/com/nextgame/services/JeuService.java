package com.nextgame.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgame.entities.Genre;
import com.nextgame.entities.Jeu;
import com.nextgame.repositories.IJeuRepository;

@Service
public class JeuService implements IService<Jeu, Long> {

	@Autowired
	IJeuRepository jeuRepository;
	
	/** 
	 * Retourne une liste de tous les jeux
	 * @return List<Jeu>
	 */
	@Override
	public List<Jeu> getAll() {
		return jeuRepository.findAll();
	}

	/**
	 * Retourne un objet jeu en fonction de l'id passé en paramètre
	 * @param id
	 * @return Jeu
	 */
	@Override
	public Jeu getById(Long id) {
		return jeuRepository.getReferenceById(id);
	}

	/**
	 * Permet d'enregistrer un jeu et le retourne
	 * @param jeu
	 * @return Jeu
	 */
	@Override
	public Jeu save(Jeu jeu) {
		return jeuRepository.save(jeu);
	}

	/**
	 * Met à jour l'éditeur passé dans le body
	 * @param jeu
	 * @return Jeu
	 */
	@Override
	public Jeu update(Jeu jeu) {
		return jeuRepository.saveAndFlush(jeu);
	}

	/**
	 * Supprime l'élément correpondant à l'id passé en paramètre
	 * @param id du jeu
	 */
	@Override
	public void delete(Long id) {
		jeuRepository.deleteById(id);
	}
	
	public List<Genre> getJeuGenres(Long id) {
		return jeuRepository.getReferenceById(id).getGenres();
	}
	
	public boolean existById(Long id) {
		return jeuRepository.existsById(id);
	}
}