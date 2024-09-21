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

import com.nextgame.dtos.EditeurDTO;
import com.nextgame.entities.Editeur;
import com.nextgame.mappers.EditeurMapperImpl;
import com.nextgame.services.EditeurService;

/**
 * Contrôleur REST exposant les endpoints d'un Editeur, récupération, ajout, modifications, etc.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/editeurs")
public class EditeurController {
	
	@Autowired
	EditeurService editeurService;
	
	@Autowired
	EditeurMapperImpl editeurMapperImpl;
	
	private static final Logger logger = LogManager.getLogger(EditeurController.class);
	
	/**
	 * Retourne une liste avec tous les éditeurs.
	 * @return ResponseEntity<List<EditeurDTO>>
	 */
	@GetMapping()
	public ResponseEntity<List<EditeurDTO>> getAllEditeurs (){
		logger.info("EditeurController - getAllEditeurs");
		List<EditeurDTO> listeEditeursDto = editeurService.getAll()
				.stream().map(editeurMapperImpl::mapToDto).collect(Collectors.toList());
		
		if (listeEditeursDto.isEmpty()) {
	            logger.info("Aucun éditeur trouvé.");
	            return ResponseEntity.noContent().build();
        }
		logger.info("Editeurs trouvés : {}", listeEditeursDto.size());
		return ResponseEntity.ok(listeEditeursDto);
	}
	
	/**
	 * Retourne l'editeur en fonction de l'id passé dans le path.
	 * @param id
	 * @return ResponseEntity<EditeurDTO>
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<EditeurDTO> getEditeurById(@PathVariable Long id) {
		logger.info("EditeurController - getEditeurById - id : {}", id);
		
		// Vérifie que l'id existe
		if(editeurService.existById(id)) {
			EditeurDTO editeurDTO = editeurMapperImpl.mapToDto(editeurService.getById(id));
			return ResponseEntity.ok(editeurDTO);
		}
		else {
			logger.warn("L'éditeur avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Permet de créer un nouvel éditeur.
	 * @param newEditeur
	 * @return ResponseEntity<EditeurDTO>
	 */
	@PostMapping
	public ResponseEntity<EditeurDTO> post(@RequestBody EditeurDTO newEditeur) {
		logger.info("EditeurController - post");
		EditeurDTO editeurEnr = editeurMapperImpl.mapToDto(editeurService.save(editeurMapperImpl.mapToEntity(newEditeur)));
		return ResponseEntity.status(HttpStatus.CREATED).body(editeurEnr);
	}
	
	/**
	 * Met à jour un éditeur en fonction de l'id dans le path.
	 * @param editeurDTO
	 * @param id
	 * @return ResponseEntity<EditeurDTO>
	 */
	@PutMapping(path = "/{id}")
	public ResponseEntity<EditeurDTO> update (@RequestBody EditeurDTO editeurDTO, @PathVariable long id) {
		logger.info("EditeurController - update");
		// Vérifie que l'id existe
		if(editeurService.existById(id)) {
		    Editeur editeur = editeurService.getById(id);
		    editeur.setNom(editeurDTO.getNom());
		    return ResponseEntity.ok(editeurMapperImpl.mapToDto(editeurService.update(editeur)));
		}
		else {
			logger.warn("L'éditeur avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Supprime un éditeur en fonction de l'id dans le path.
	 * @param id
	 * @return ResponseEntity avec le statut HTTP approprié. 
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("EditeurController - delete - id : {}", id);
		// Vérifie que l'id existe
		if (editeurService.existById(id)){
			editeurService.delete(id);
			logger.info("L'éditeur avec l'id {} a été supprimé.", id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			logger.info("L'éditeur avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}