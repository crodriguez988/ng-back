package com.nextgame.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

/**
 * Contrôleur REST exposant les endpoints d'une plateforme, récupération, ajout, modifications, etc.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/plateformes")
public class PlateformeController {
	
	@Autowired
	PlateformeService plateformeService;
	
	@Autowired
	PlateformeMapperImpl plateformeMapperImpl;
	
	private static final Logger logger = LogManager.getLogger(PlateformeController.class);
	
	/**
	 * Retourne une liste avec toutes les plateformes.
	 * @return ResponseEntity<List<PlateformeDTO>>
	 */
	@GetMapping()
	public ResponseEntity<List<PlateformeDTO>> getAllPlateformes(){
		logger.info("PlateformeController - getAllPlateformes");
		List<PlateformeDTO> listePlateformeDto = plateformeService.getAll()
				.stream().map(plateformeMapperImpl::mapToDto).collect(Collectors.toList());
		
		if (listePlateformeDto.isEmpty()) {
	            logger.info("Aucune plateforme trouvée.");
	            return ResponseEntity.noContent().build();
        }
		logger.info("Plateformes trouvées : {}", listePlateformeDto.size());
		return ResponseEntity.ok(listePlateformeDto);
	}
	
	/**
	 * Retourne la plateforme en fonction de l'id dans le path.
	 * @param id
	 * @return ResponseEntity<PlateformeDTO>
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<PlateformeDTO> getPlateformeById(@PathVariable Long id) {
		logger.info("PlateformeController - getPlateformeById - id : {}", id);
		// Vérifie que l'id existe
		if(plateformeService.existById(id)) {
			PlateformeDTO PlateformeDTO = plateformeMapperImpl.mapToDto(plateformeService.getById(id));
			return ResponseEntity.ok(PlateformeDTO);
		}
		else {
			logger.warn("Le plateforme avec l'id {} n'a pas été trouvée.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Crée une nouvelle plateforme.
	 * @param newPlateformeDTO
	 * @return PlateformeDTO
	 */
	@PostMapping
	public ResponseEntity<PlateformeDTO> post(@RequestBody PlateformeDTO newPlateformeDTO) {
		logger.info("PlateformeController - post");
		return ResponseEntity.status(HttpStatus.CREATED).body(plateformeMapperImpl.mapToDto(plateformeService.save(plateformeMapperImpl.mapToEntity(newPlateformeDTO))));
	}
	
	/**
	 * Met à jour une plateforme en fonction de l'id dans le path.
	 * @param PlateformeDTO
	 * @param id
	 * @return ResponseEntity<PlateformeDTO>
	 */
	@PutMapping(path = "/{id}")
	public ResponseEntity<PlateformeDTO> update (@RequestBody(required = true) PlateformeDTO plateformeDTO, @PathVariable long id) {
		logger.info("PlateformeController - update");
		// Vérifie que l'id existe
		if(plateformeService.existById(id)) {
			Plateforme plateforme = plateformeService.getById(id);
			plateforme.setNom(plateformeDTO.getNom());
			return ResponseEntity.ok(plateformeMapperImpl.mapToDto(plateformeService.update(plateforme)));
		}
		else {
			logger.warn("La plateforme avec l'id {} n'a pas été trouvée.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Supprime une Plateforme en fonction de l'id dans le path.
	 * @param id
	 * @return ResponseEntity avec le statut HTTP approprié. 
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("PlateformeController - delete - id : {}", id);
		// Vérifie que l'id existe
		if (plateformeService.existById(id)) {
		    plateformeService.delete(id);
		    logger.info("La plateforme avec l'id {} a été supprimée.", id);
		    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
		    logger.warn("La plateforme avec l'id {} n'a pas été trouvée.", id);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}