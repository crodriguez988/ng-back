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

import com.nextgame.dtos.StudioDevDTO;
import com.nextgame.entities.StudioDev;
import com.nextgame.mappers.StudioDevMapperImpl;
import com.nextgame.services.StudioDevService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/studiosDev")
public class StudioDevController {
	
	@Autowired
	StudioDevService studioDevService;
	
	@Autowired
	StudioDevMapperImpl studioDevMapperImpl;
	
	/**
	 * EndPoint GET : Retourne une liste de studioDev
	 * @return List<StudioDevDTO>
	 */
	@GetMapping()
	public List<StudioDevDTO> getAllStudioDev (){
		List<StudioDevDTO> listeSudiosDevDto = new ArrayList<>();
		studioDevService.getAll().forEach(studioDev -> listeSudiosDevDto.add(studioDevMapperImpl.mapToDto(studioDev)));
		System.err.println(listeSudiosDevDto);
		
		return listeSudiosDevDto;
	}
	
	/**
	 * Endpoint GET /id : Retourne l'objet studioDev en fonction de l'id dans le path
	 * @param id
	 * @return StudioDevDTO
	 */
	@GetMapping(path = "/{id}")
	public StudioDevDTO getById(@PathVariable Long id) {
		// Vérifie que l'id existe
		if(studioDevService.existById(id)) {
			StudioDevDTO studioDevDTO = studioDevMapperImpl.mapToDto(studioDevService.getById(id));
			return studioDevDTO;
		}
		else {
			throw new EntityNotFoundException();
		}
	}
	
	/**
	 * Endpoint POST : Permet de créer un nouveau studio
	 * @param newStudioDevDTO
	 * @return StudioDevDTO
	 */
	@PostMapping
	public StudioDevDTO post(@RequestBody StudioDevDTO newStudioDevDTO) {
		return studioDevMapperImpl.mapToDto(studioDevService.save(studioDevMapperImpl.mapToEntity(newStudioDevDTO)));
	}
	
	/**
	 * Endpoint PUT : Permet de mettre à jour un Studio en fonction de l'id dans le path
	 * @param studioDevDTO
	 * @param id
	 * @return StudioDevDTO
	 */
	@PutMapping(path = "/{id}")
	public StudioDevDTO update (@RequestBody(required = true) StudioDevDTO studioDevDTO, @PathVariable long id) {
		// Vérifie que l'id existe
		if(studioDevService.existById(id)) {
			StudioDev studioDev = studioDevService.getById(id);
			studioDev.setNom(studioDevDTO.getNom());
			
			return studioDevMapperImpl.mapToDto(studioDevService.update(studioDev));
		}
		else {
			throw new EntityNotFoundException();
		}
	}
	
	/**
	 * Endpoint DELETE : Permet de supprimer un StudioDeveloppeur en fonction de l'id dans le path
	 * @param id
	 */
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Long id) {
		// Vérifie que l'id existe
		if(studioDevService.existById(id)) {
			studioDevService.delete(id);
		}
		else {
			throw new EntityNotFoundException();
		}
	}
}