package com.nextgame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.nextgame.entities.StudioDev;
import com.nextgame.repositories.IStudioDevRespository;

/**
 * 
 */
@Service
public class StudioDevService implements IService<StudioDev, Long>{
	
	@Autowired
	IStudioDevRespository studioDevRespository;
	
	/** 
	 * Retourne une liste contenant tous les studios développeurs
	 * @return List<StudioDev>
	 */
	@Override
	public List<StudioDev> getAll() {
		List<StudioDev> studioDevs = new ArrayList<StudioDev>();
		studioDevs.addAll(studioDevRespository.findAll());
		return studioDevs;
	}
	
	/**
	 * Retourne un objet StudioDev en fonction de l'id passé en paramètre
	 * @param id
	 * @return StudioDev
	 */
	@Override
	public StudioDev getById(@PathVariable Long id) {
		return studioDevRespository.getReferenceById(id);
	}
	
	/**
	 * Permet d'enregistrer un studio de développement et le retourne
	 * @param studioDev
	 * @return StudioDev
	 */
	@Override
	public StudioDev save (StudioDev studioDev) {
		return studioDevRespository.save(studioDev);
	}
	
	/**
	 * Met à jour le studioDev passé dans le body
	 * @param studioDev
	 * @return StudioDev
	 */
	@Override
	public StudioDev update (StudioDev studioDev) {
		return studioDevRespository.saveAndFlush(studioDev);
	}
	
	/**
	 * Supprime l'élément en fonction de l'id passé en paramètre
	 * @param id du Studio
	 */
	@Override
	public void delete (Long id) {
		studioDevRespository.deleteById(id);
	}
	
	@Override
	public boolean existById(Long id) {
		return studioDevRespository.existsById(id);
	}
}