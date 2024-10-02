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

import com.nextgame.dtos.UtilisateurDTO;
import com.nextgame.entities.Utilisateur;
import com.nextgame.mappers.UtilisateurMapperImpl;
import com.nextgame.services.UtilisateurService;

/**
 * Contrôleur REST exposant les endpoints d'un utilisateur, récupération, ajout, modifications, etc.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/utilisateurs")
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	UtilisateurMapperImpl utilisateurMapperImpl;
	
	private static final Logger logger = LogManager.getLogger(UtilisateurController.class);
	
	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs() { 
		logger.info("UtilisateurController : getAllUtilisateurs");
		List<UtilisateurDTO> listeUtilisateursDto = utilisateurService.getAll()
	            .stream().map(utilisateurMapperImpl::mapToDto).collect(Collectors.toList());
		
		 if (listeUtilisateursDto.isEmpty()) {
		        logger.info("Aucun utilisateur trouvé.");
		        // Code 204 (No Content) si la liste est vide
		        return ResponseEntity.noContent().build();  
			 }

			 logger.info("Nombre d'utilisateurs trouvés: {}", listeUtilisateursDto.size());
		return ResponseEntity.ok(listeUtilisateursDto);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable Long id) {
		logger.info("UtilisateurController : getUtilisateurById - id : {}", id);
		// Vérifie que l'id existe
		if(utilisateurService.existById(id)) {
			return ResponseEntity.ok(utilisateurMapperImpl.mapToDto(utilisateurService.getById(id)));
		}
		else {
			logger.warn("L'utilisateur avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Crée un nouvel utilisateur.
	 * @param newutilisateur
	 * @return ResponseEntity<UtilisateurDTO>
	 */
	@PostMapping
	public ResponseEntity<UtilisateurDTO> post(@RequestBody UtilisateurDTO newUtilisateur) {
		logger.info("UtilisateurController : post");
		logger.info("new user  :  {} ", newUtilisateur);
		return ResponseEntity.ok(utilisateurMapperImpl.mapToDto(utilisateurService.save(utilisateurMapperImpl.mapToEntity(newUtilisateur))));
	}
	
	/**
	 * Met à jour un utilisateur en fonction de l'id dans le path.
	 * @param utilisateurDTO
	 * @param id
	 * @return ResponseEntity<UtilisateurDTO>
	 */
	@PutMapping(path = "/{id}")
	public ResponseEntity<UtilisateurDTO> update (@RequestBody UtilisateurDTO utilisateurDTO, @PathVariable long id) {
		logger.info("UtilisateurController : update");
	    // Vérifie que l'id existe 
	    if(utilisateurService.existById(id)) {
	    	Utilisateur utilisateur = utilisateurService.getById(id);
	    	utilisateur.setEmail(utilisateurDTO.getEmail());
	    	utilisateur.setPseudonyme(utilisateurDTO.getPseudonyme());
	    	
		    return ResponseEntity.ok(utilisateurMapperImpl.mapToDto(utilisateurService.update(utilisateur)));
	    }
	    else {
	    	logger.warn("L'utilisateur avec l'id {} n'a pas été trouvé.", id);
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}
	
	/**
	 * Supprime l'utilisateur en fonction de l'id dans le path.
	 * @param id
	 * @return ResponseEntity avec le statut HTTP approprié. 
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("UtilisateurController : delete - id : {}", id);
		// Vérifie que l'id existe
		if(utilisateurService.existById(id)) {
			utilisateurService.delete(id);
			logger.info("L'utilisateur avec l'id {} a été supprimé.", id);
		    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			logger.warn("L'utilisateur avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}