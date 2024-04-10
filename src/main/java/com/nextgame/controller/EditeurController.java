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

import com.nextgame.dtos.EditeurDTO;
import com.nextgame.entities.Editeur;
import com.nextgame.mappers.EditeurMapperImpl;
import com.nextgame.services.EditeurService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/editeurs")
public class EditeurController {
	
	@Autowired
	EditeurService editeurService;
	
	@Autowired
	EditeurMapperImpl editeurMapperImpl;
	
	/**
	 * EndPoint GET : Retourne une liste d'éditeurs
	 * @return List<EditeurDTO>
	 */
	@GetMapping()
	public List<EditeurDTO> getAllEditeurs (){
		List<EditeurDTO> listEditeurDto = new ArrayList<>();
		editeurService.getAll().forEach(editeur -> listEditeurDto.add(editeurMapperImpl.mapToDto(editeur)));
		System.err.println(listEditeurDto);
		
		return listEditeurDto;
	}
	
	/**
	 * Endpoint GET /id : Retourne l'objet editeur en fonction de l'id passé dans le path
	 * @param id
	 * @return EditeurDTO
	 */
	@GetMapping(path = "/{id}")
	public EditeurDTO getById(@PathVariable Long id) {
		//TODO: Gerer si l'id n'existe pas
		EditeurDTO EditeurDTO = editeurMapperImpl.mapToDto(editeurService.getById(id));
		
		return EditeurDTO;
	}
	
	/**
	 * Endpoint POST : Permet de créer un nouvel éditeur
	 * @param newEditeur
	 * @return EditeurDTO
	 */
	@PostMapping
	public EditeurDTO post(@RequestBody EditeurDTO newEditeur) {
		return editeurMapperImpl.mapToDto(editeurService.save(editeurMapperImpl.mapToEntity(newEditeur)));
	}
	
	/**
	 * Endpoint PUT : Permet de mettre à jour un éditeur en fonction de l'id dans le path
	 * @param editeurDTO
	 * @param id
	 * @return EditeurDTO
	 */
	@PutMapping(path = "/{id}")
	public EditeurDTO update (@RequestBody EditeurDTO editeurDTO, @PathVariable long id) {
	    // Vérification que l'id existe
	    Editeur editeur = editeurService.getById(id);
	    editeur.setNom(editeurDTO.getNom());
	    
	    return editeurMapperImpl.mapToDto(editeurService.update(editeur));
	}
	
	/**
	 * Endpoint DELETE : Permet de supprimer un éditeur en fonction de l'id dans le path
	 * @param id
	 */
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Long id) {
		//TODO: Vérifier si l'id existe
		editeurService.delete(id);
	}
}