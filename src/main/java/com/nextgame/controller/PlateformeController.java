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

import com.nextgame.dtos.PlateformeDTO;
import com.nextgame.entities.Plateforme;
import com.nextgame.mappers.PlateformeMapperImpl;
import com.nextgame.services.PlateformeService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/plateformes")
public class PlateformeController {
	
	@Autowired
	PlateformeService plateformeService;
	
	@Autowired
	PlateformeMapperImpl plateformeMapperImpl;
	
	/**
	 * EndPoint GET : Retourne une liste des plateformes
	 * @return List<PlateformeDTO>
	 */
	@GetMapping()
	public List<PlateformeDTO> getAll(){
		List<PlateformeDTO> listePlateformeDto = new ArrayList<>();
		plateformeService.getAll().forEach(plateforme -> listePlateformeDto.add(plateformeMapperImpl.mapToDto(plateforme)));
		System.err.println(listePlateformeDto);
		
		return listePlateformeDto;
	}
	
	/**
	 * Endpoint GET /id : Retourne l'objet plateforme en fonction de l'id dans le path
	 * @param id
	 * @return PlateformeDTO
	 */
	@GetMapping(path = "/{id}")
	public PlateformeDTO getById(@PathVariable Long id) {
		 // Vérifie que l'id existe 
	    if(plateformeService.existById(id)) {
	    	PlateformeDTO PlateformeDTO = plateformeMapperImpl.mapToDto(plateformeService.getById(id));
			return PlateformeDTO;
	    }
	    else {
	    	throw new EntityNotFoundException();
	    }
	}
	
	/**
	 * Endpoint POST : Permet de créer une nouvelle plateforme
	 * @param newPlateformeDTO
	 * @return PlateformeDTO
	 */
	@PostMapping
	public PlateformeDTO post(@RequestBody PlateformeDTO newPlateformeDTO) {
		return plateformeMapperImpl.mapToDto(plateformeService.save(plateformeMapperImpl.mapToEntity(newPlateformeDTO)));
	}
	
	/**
	 * Endpoint PUT : Mise à jour une plateforme en fonction de l'id dans le path
	 * @param PlateformeDTO
	 * @param id
	 * @return PlateformeDTO
	 */
	@PutMapping(path = "/{id}")
	public PlateformeDTO update (@RequestBody(required = true) PlateformeDTO plateformeDTO, @PathVariable long id) {
		 // Vérifie que l'id existe 
	    if(plateformeService.existById(id)) {
	    	Plateforme plateforme = plateformeService.getById(id);
			plateforme.setNom(plateformeDTO.getNom());
			plateforme.setAbreviation(plateformeDTO.getAbreviation());
			
			return plateformeMapperImpl.mapToDto(plateformeService.update(plateforme));
	    }
		else {
	    	throw new EntityNotFoundException();
	    }
	}
	
	/**
	 * Endpoint DELETE : Permet de supprimer une Plateforme en fonction de l'id dans le path
	 * @param id
	 */
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Long id) {
		 // Vérifie que l'id existe 
	    if(plateformeService.existById(id)) {
	    	plateformeService.delete(id);
	    }
		else {
		    	throw new EntityNotFoundException();
	  }
	}
}