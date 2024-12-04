package com.nextgame.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgame.dtos.JeuxDetailsDTO;
import com.nextgame.entities.Jeu;
import com.nextgame.repositories.IJeuRepository;

/**
 * Ce service contient tous les appels CRUD et plus, du repository Jeu.
 */
@Service
public class JeuService implements IService<Jeu, Long> {

	@Autowired
	IJeuRepository jeuRepository;
	
	/** 
	 * Retourne une liste de tous les jeux.
	 * @return List<Jeu>
	 */
	@Override
	public List<Jeu> getAll() {
		return jeuRepository.findAll();
	}

	/**
	 * Retourne un objet jeu en fonction de l'id passé en paramètre.
	 * @param id
	 * @return Jeu
	 */
	@Override
	public Jeu getById(Long id) {
		return jeuRepository.getReferenceById(id);
	}

	/**
	 * Permet d'enregistrer un jeu et le retourne.
	 * @param jeu
	 * @return Jeu
	 */	
	@Override
	public Jeu save(Jeu jeu) {
		return jeuRepository.save(jeu);
	}

	/**
	 * Met à jour l'éditeur passé dans le body.
	 * @param jeu
	 * @return Jeu
	 */
	@Override
	public Jeu update(Jeu jeu) {
		return jeuRepository.saveAndFlush(jeu);
	}

	/**
	 * Supprime l'élément correpondant à l'id passé en paramètre.
	 * @param id du jeu
	 */
	@Override
	public void delete(Long id) {
		jeuRepository.deleteById(id);
	}
	
	/**
	 * Verifie si l'id existe.
	 * @param id
	 * @return boolean
	 */
	public boolean existById(Long id) {
		return jeuRepository.existsById(id);
	}
	
	/**
	 * Retourne une liste de jeux en fonction de l'année passée en parametre.
	 * @param idGenre
	 * @return List<Jeu>
	 */
	public List<Jeu> getAllJeuxByGenreId (Long idGenre){
		return jeuRepository.findByGenresId(idGenre);
	}
	
	/**
	 * Retourne une liste avec les jeux appartenant à la même franchise qu'un jeu.
	 * List<Jeu>
	 * @param idJeu
	 * @return List<Jeu>
	 */
	public List<Jeu> getJeuxWithSameFranchise(Long idFranchise) {
        return jeuRepository.findByFranchiseId(idFranchise);
	}
	
	/**
	 * Retourne une liste de jeux qui ont le même développeur.
	 * @param idDev
	 * @return List<Jeu>
	 */
	public List<Jeu> getAllJeuxByDeveloppeurId(Long idDev) {
		return jeuRepository.findByDeveloppeursId(idDev);
	}
	
	/**
	 * Retourne une liste de jeux en fonction de l'année passée en parametre.
	 * @param annee
	 * @return List<Jeu>
	 */
	public List<Jeu> getAllJeuxByAnneeSortie (int annee) {
		return jeuRepository.findByAnneSortie(annee);
	}
	
	/**
	 * 
	 * @return List<JeuDetailsDTO>
	 */
	public List<JeuxDetailsDTO>getListeJeuxDetails() {
		return jeuRepository.findAllJeuxDetails();
	}
}