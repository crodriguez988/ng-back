package com.nextgame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgame.entities.Plateforme;
import com.nextgame.repositories.IPlateformeRepository;

/**
 *  Ce service contient tous les appels CRUD du repository Plateforme.
 */
@Service
public class PlateformeService implements IService<Plateforme, Long>{

	@Autowired
	IPlateformeRepository plateformeRepository;

	/** 
	 * Retourne une liste contenant toutes les plateformes
	 * @return List<Plateforme>
	 */
	@Override
	public List<Plateforme> getAll() {
		List<Plateforme> plateformes = new ArrayList<Plateforme>();
		plateformes.addAll(plateformeRepository.findAll());
		return plateformes;
	}

	/**
	 * Retourne un objet Plateforme en fonction de l'id passé en paramètre
	 * @param id
	 * @return Plateforme
	 */
	@Override
	public Plateforme getById(Long id) {
		return plateformeRepository.getReferenceById(id);
	}

	/**
	 * Permet d'enregistrer une nouvelle plateforme et la retourne
	 * @param plateforme
	 * @return Plateforme
	 */
	@Override
	public Plateforme save(Plateforme plateforme) {
		return plateformeRepository.save(plateforme);
	}

	/**
	 * Met à jour la plateforme passée dans le body
	 * @param plateforme
	 * @return plateforme
	 */
	@Override
	public Plateforme update(Plateforme plateforme) {
		return plateformeRepository.saveAndFlush(plateforme);
	}

	/**
	 * Supprime l'élément correpondant à l'id passé en paramètre
	 * @param id de la plateforme
	 */
	@Override
	public void delete(Long id) {
		plateformeRepository.deleteById(id);
	}

	/**
	 * Vérifie que l'id passé en param existe.
	 * @param id
	 * @return boolean
	 */
	@Override
	public boolean existById(Long id) {
		return plateformeRepository.existsById(id);
	}
}