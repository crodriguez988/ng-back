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

import com.nextgame.dtos.DeveloppeurDTO;
import com.nextgame.entities.Developpeur;
import com.nextgame.mappers.DeveloppeurMapperImpl;
import com.nextgame.services.DeveloppeurService;

/**
 * Contrôleur REST exposant les endpoints d'un développeur, récupération, ajout, modifications, etc.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/developpeurs")
public class DeveloppeurController {
	
	@Autowired
	DeveloppeurService developpeurService;
	
	@Autowired
	DeveloppeurMapperImpl developpeurMapperImpl;
	
	private static final Logger logger = LogManager.getLogger(DeveloppeurController.class);
	
	/**
	 * Retourne une liste avec tous les developpeurs.
	 * @return ResponseEntity<List<DeveloppeurDTO>>
	 */
	@GetMapping()
	public ResponseEntity<List<DeveloppeurDTO>> getAllDeveloppeurs (){
		logger.info("DeveloppeurController - getAllDeveloppeurs");
		List<DeveloppeurDTO> listeDeveloppeursDto = developpeurService.getAll()
				.stream().map(developpeurMapperImpl::mapToDto).collect(Collectors.toList());
		
		if (listeDeveloppeursDto.isEmpty()) {
            logger.info("Aucun développeur trouvé.");
            return ResponseEntity.noContent().build();
        }
		logger.info("Développeurs trouvés : {}", listeDeveloppeursDto.size());
		return ResponseEntity.ok(listeDeveloppeursDto);
	}
	
	/**
	 * Retourne un developpeur en fonction de l'id dans le path.
	 * @param id
	 * @return ResponseEntity<DeveloppeurDTO>
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<DeveloppeurDTO> getDeveloppeurById(@PathVariable Long id) {
		logger.info("DeveloppeurController : getDeveloppeurById - id : {}", id);
		
		// Vérifie que l'id existe
		if(developpeurService.existById(id)) {
			DeveloppeurDTO developpeurDTO = developpeurMapperImpl.mapToDto(developpeurService.getById(id));
			return ResponseEntity.ok(developpeurDTO);
		}
		else {
			logger.warn("Le développeur avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Crée un nouveau développeur.
	 * @param newDeveloppeurDTO
	 * @return ResponseEntity<DeveloppeurDTO>
	 */
	@PostMapping
	public ResponseEntity<DeveloppeurDTO> post(@RequestBody DeveloppeurDTO newDeveloppeurDTO) {
		logger.info("DeveloppeurController : post");
		DeveloppeurDTO developpeurEnr = developpeurMapperImpl.mapToDto(developpeurService.save(developpeurMapperImpl.mapToEntity(newDeveloppeurDTO)));
		return ResponseEntity.status(HttpStatus.CREATED).body(developpeurEnr);
	}
	
	/**
	 * Met à jour un développeur en fonction de l'id dans le path.
	 * @param developpeurDTO
	 * @param id
	 * @return ResponseEntity<DeveloppeurDTO>
	 */
	@PutMapping(path = "/{id}")
	public ResponseEntity<DeveloppeurDTO> update (@RequestBody(required = true) DeveloppeurDTO developpeurDTO, @PathVariable long id) {
		logger.info("DeveloppeurController : update");
		// Vérifie que l'id existe
		if(developpeurService.existById(id)) {
			Developpeur developpeur = developpeurService.getById(id);
			developpeur.setNom(developpeurDTO.getNom());
			return ResponseEntity.ok(developpeurMapperImpl.mapToDto(developpeurService.update(developpeur)));
		}
		else {
			logger.warn("Le développeur avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Supprime un Developpeur en fonction de l'id dans le path.
	 * @param id
	 * @return ResponseEntity avec le statut HTTP approprié. 
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("DeveloppeurController : delete - id : {}", id);
		// Vérifie que l'id existe
		if (developpeurService.existById(id)) {
		    developpeurService.delete(id);
		    logger.info("Le développeur avec l'id {} a été supprimé.", id);
		    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
		    logger.warn("Le développeur avec l'id {} n'a pas été trouvé.", id);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}