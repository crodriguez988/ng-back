package com.nextgame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.nextgame.entities.Developpeur;
import com.nextgame.repositories.IDeveloppeurRespository;

/**
 * Ce service contient tous les appels CRUD et plus du repository Developpeur.
 */
@Service
public class DeveloppeurService implements IService<Developpeur, Long>{
	
	@Autowired
	IDeveloppeurRespository developpeurRespository;
	
	/** 
	 * Retourne une liste contenant tous les studios développeurs.
	 * @return List<Developpeur>
	 */
	@Override
	public List<Developpeur> getAll() {
		List<Developpeur> listeDeveloppeurs = new ArrayList<Developpeur>();
		listeDeveloppeurs.addAll(developpeurRespository.findAll());
		return listeDeveloppeurs;
	}
	
	/**
	 * Retourne un objet Developpeur en fonction de l'id passé en paramètre.
	 * @param id
	 * @return Developpeur
	 */
	@Override
	public Developpeur getById(@PathVariable Long id) {
		return developpeurRespository.getReferenceById(id);
	}
	
	/**
	 * Permet d'enregistrer un studio de développement et le retourne.
	 * @param Developpeur
	 * @return Developpeur
	 */
	@Override
	public Developpeur save (Developpeur developpeur) {
		return developpeurRespository.save(developpeur);
	}
	
	/**
	 * Met à jour le developpeur passé dans le body.
	 * @param developpeur
	 * @return Developpeur
	 */
	@Override
	public Developpeur update (Developpeur developpeur) {
		return developpeurRespository.saveAndFlush(developpeur);
	}
	
	/**
	 * Supprime l'élément en fonction de l'id passé en paramètre.
	 * @param id du developpeur
	 */
	@Override
	public void delete (Long id) {
		developpeurRespository.deleteById(id);
	}
	
	/**
	 * Vérifie que l'id passé en param existe.
	 * @param id
	 * @return boolean
	 */
	@Override
	public boolean existById(Long id) {
		return developpeurRespository.existsById(id);
	}
}