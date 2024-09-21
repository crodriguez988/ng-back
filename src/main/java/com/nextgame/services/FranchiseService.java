package com.nextgame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.nextgame.entities.Franchise;
import com.nextgame.repositories.IFranchiseRepository;

/**
 * Cette classe contient tous les appels CRUD du repository Franchise.
 */
@Service
public class FranchiseService implements IService<Franchise, Long>{
	
	@Autowired
	IFranchiseRepository franchiseRespository;

	@Override
	public List<Franchise> getAll() {
		List<Franchise> Franchise = new ArrayList<Franchise>();
		Franchise.addAll(franchiseRespository.findAll());
		return Franchise;
	}
	
	/**
	 * Retourne un objet Franchise en fonction de l'id passé en paramètre.
	 * @param id
	 * @return Franchise
	 */
	@Override
	public Franchise getById(@PathVariable Long id) {
		return franchiseRespository.getReferenceById(id);
	}
	
	/**
	 * Permet d'enregistrer une franchise et la retourne.
	 * @param Franchise
	 * @return Franchise
	 */
	@Override
	public Franchise save (Franchise Franchise) {
		return franchiseRespository.save(Franchise);
	}
	
	/**
	 * Met à jour le Franchise passé dans le body.
	 * @param Franchise
	 * @return Franchise
	 */
	@Override
	public Franchise update (Franchise Franchise) {
		return franchiseRespository.saveAndFlush(Franchise);
	}
	
	/**
	 * Supprime l'élément en fonction de l'id passé en paramètre.
	 * @param id du Franchise
	 */
	@Override
	public void delete (Long id) {
		franchiseRespository.deleteById(id);
	}
	
	/**
	 * Vérifie que l'id passé en param existe.
	 * @param id
	 * @return boolean
	 */
	@Override
	public boolean existById(Long id) {
		return franchiseRespository.existsById(id);
	}
	
}