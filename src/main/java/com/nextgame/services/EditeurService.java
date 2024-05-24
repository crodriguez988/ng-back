package com.nextgame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgame.entities.Editeur;
import com.nextgame.repositories.IEditeurRepository;

@Service
public class EditeurService implements IService<Editeur, Long>{

	@Autowired
	IEditeurRepository editeurRespository;
	
	/** 
	 * Retourne une liste de tous les éditeurs
	 * @return List<Editeur>
	 */
	@Override
	public List<Editeur> getAll() {
		List<Editeur> editeurs = new ArrayList<Editeur>();
		editeurs.addAll(editeurRespository.findAll());
		return editeurs;
	}

	/**
	 * Retourne un objet editeur en fonction de l'id passé en paramètre
	 * @param id
	 * @return Editeur
	 */
	@Override
	public Editeur getById(Long id) {
		return editeurRespository.getReferenceById(id);
	}

	/**
	 * Permet d'enregistrer un éditeur et le retourne
	 * @param editeur
	 * @return Editeur
	 */
	@Override
	public Editeur save(Editeur editeur) {
		return editeurRespository.save(editeur);
	}

	/**
	 * Met à jour l'éditeur passé dans le body
	 * @param editeur
	 * @return Editeur
	 */
	@Override
	public Editeur update(Editeur editeur) {
		return editeurRespository.save(editeur);
	}

	/**
	 * Supprime l'élément correpondant à l'id passé en paramètre
	 * @param id de l'éditeur
	 */
	@Override
	public void delete(Long id) {
		editeurRespository.deleteById(id);
	}

	@Override
	public boolean existById(Long id) {
		return editeurRespository.existsById(id);
	}
}