package com.nextgame.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextgame.dtos.JeuDTO;
import com.nextgame.entities.Jeu;
import com.nextgame.mappers.JeuMapperImpl;
import com.nextgame.services.JeuService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/jeux")
public class JeuController {
	
	@Autowired
	JeuService jeuService;
	
	@Autowired
	JeuMapperImpl jeuMapperImpl;

	/**
	 * EndPoint GET : Retourne une liste de jeux
	 * @return List<JeuDTO>
	 */
	@GetMapping()
	public List<JeuDTO> getAllJeux (){
		List<JeuDTO> listeJeuxDto = new ArrayList<>();
		jeuService.getAll().forEach(jeu -> listeJeuxDto.add(jeuMapperImpl.mapToDto(jeu)));
		
		return listeJeuxDto;
	}
	
	/**
	 * Endpoint GET /id : Retourne l'objet jeu en fonction de l'id passé dans le path
	 * @param id
	 * @return JeuDTO
	 */
	@GetMapping(path = "/{id}")
	public JeuDTO getById(@PathVariable Long id) {
		// Vérifie que l'id existe
		if(jeuService.existById(id)) {
			return jeuMapperImpl.mapToDto(jeuService.getById(id));
		}
		else {
			throw new EntityNotFoundException();
		}
	}
	
	/**
	 * Endpoint POST : Permet de créer un novueau jeu
	 * @param newJeu
	 * @return JeuDTO
	 */
	@PostMapping
	public JeuDTO post(@RequestBody JeuDTO newJeu) {
		return jeuMapperImpl.mapToDto(jeuService.save(jeuMapperImpl.mapToEntity(newJeu)));
	}
	
	/**
	 * Endpoint PUT : Permet de mettre à jour un jeu en fonction de l'id dans le path
	 * @param jeuDTO
	 * @param id
	 * @return JeuDTO
	 */
	@PutMapping(path = "/{id}")
	public JeuDTO update (@RequestBody JeuDTO jeuDTO, @PathVariable long id) {
	    // Vérifie que l'id existe 
	    if(jeuService.existById(id)) {
	    	Jeu jeu = jeuService.getById(id);
		    jeu.setNom(jeuDTO.getNom());
		    
		    return jeuMapperImpl.mapToDto(jeuService.update(jeu));
	    }
	    else {
	    	throw new EntityNotFoundException();
	    }
	}
	
	/**
	 * Endpoint DELETE : Permet de supprimer un jeu en fonction de l'id dans le path
	 * @param id
	 */
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Long id) {
		 // Vérifie que l'id existe
		 if(jeuService.existById(id)) {
			 jeuService.delete(id);}
		 else {
			 throw new EntityNotFoundException();
		 }
	}
}