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

import com.nextgame.dtos.FranchiseDTO;
import com.nextgame.entities.Franchise;
import com.nextgame.mappers.FranchiseMapperImpl;
import com.nextgame.services.FranchiseService;

import jakarta.persistence.EntityNotFoundException;

/**
 * Contrôleur REST exposant les endpoints d'une franchise, récupération, ajout, modifications, etc.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/franchises")
public class FranchiseController {
	
	@Autowired
	FranchiseService franchiseService;
	
	@Autowired
	FranchiseMapperImpl franchiseMapperImpl;
	
	private static final Logger logger = LogManager.getLogger(FranchiseController.class);
	
	/**
	 * Retourne une liste avec toutes les franchises
	 * @return ResponseEntity<List<FranchiseDTO>>
	 */
	@GetMapping()
	public ResponseEntity<List<FranchiseDTO>> getAllFranchises (){
		logger.info("FranchiseController - getAllFranchises");
		List<FranchiseDTO> listeFranchisesDto = franchiseService.getAll()
				.stream().map(franchiseMapperImpl::mapToDto).collect(Collectors.toList());
		
		if (listeFranchisesDto.isEmpty()) {
	            logger.info("Aucune franchise trouvée.");
	            return ResponseEntity.noContent().build();
        }
		logger.info("franchises trouvées : {}", listeFranchisesDto.size());
		return ResponseEntity.ok(listeFranchisesDto);
	}
	
	/**
	 * Retourne uen franchise en fonction de l'id dans le path
	 * @param id
	 * @return ResponseEntity<FranchiseDTO>
	 * @throws EntityNotFoundException si l'id n'existe pas.
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<FranchiseDTO> getFranchiseById(@PathVariable Long id) {
		logger.info("FranchiseController - getFranchiseById - id : {}", id);
		
		// Vérifie que l'id existe
		if(franchiseService.existById(id)) {
			FranchiseDTO FranchiseDTO = franchiseMapperImpl.mapToDto(franchiseService.getById(id));
			return ResponseEntity.ok(FranchiseDTO);
		}
		else {
			logger.warn("Le franchise avec l'id {} n'a pas été trouvée.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Crée une nouvelle franchise.
	 * @param newFranchiseDTO
	 * @return ResponseEntity<FranchiseDTO>
	 */
	@PostMapping
	public ResponseEntity<FranchiseDTO> post(@RequestBody FranchiseDTO newFranchiseDTO) {
		logger.info("FranchiseController - post");
		FranchiseDTO franchiseEnr = franchiseMapperImpl.mapToDto(franchiseService.save(franchiseMapperImpl.mapToEntity(newFranchiseDTO)));
		return ResponseEntity.status(HttpStatus.CREATED).body(franchiseEnr);
	}
	
	/**
	 * Met à jour une franchise en fonction de l'id dans le path.
	 * @param FranchiseDTO
	 * @param id
	 * @return ResponseEntity<FranchiseDTO>
	 */
	@PutMapping(path = "/{id}")
	public ResponseEntity<FranchiseDTO> update (@RequestBody(required = true) FranchiseDTO franchiseDTO, @PathVariable long id) {
		logger.info("FranchiseController - update");
		// Vérifie que l'id existe
		if(franchiseService.existById(id)) {
			Franchise franchise = franchiseService.getById(id);
			franchise.setNom(franchiseDTO.getNom());
			return ResponseEntity.ok(franchiseMapperImpl.mapToDto(franchiseService.update(franchise)));
		}
		else {
			logger.warn("La franchise avec l'id {} n'a pas été trouvée.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Supprime une franchise en fonction de l'id dans le path.
	 * @param id
	 * @return ResponseEntity avec le statut HTTP approprié. 
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("FranchiseController - delete - id : {}", id);
		// Vérifie que l'id existe
		if (franchiseService.existById(id)) {
		    franchiseService.delete(id);
		    logger.info("La franchise avec l'id {} a été supprimée.", id);
		    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
		    logger.warn("La franchise avec l'id {} n'a pas été trouvée.", id);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}