package com.nextgame.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextgame.dtos.JeuUtilisateurDTO;
import com.nextgame.mappers.JeuUtilisateurMapperImpl;
import com.nextgame.services.JeuUtilisateurService;
/**
 * Contrôleur REST exposant les endpoints concernant les jeux de l'utilisateur
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/utilisateurJeu")
public class JeuUtilisateurController {
	
	private static final Logger logger = LogManager.getLogger(UtilisateurController.class);
	
	@Autowired
	JeuUtilisateurService utilisateurJeuService;
	
	@Autowired
	JeuUtilisateurMapperImpl utilisateurJeuMapperImpl;
	
	/**
	 * Permet d'ajouter un jeu à la liste de jeux de l'utilisateur
	 * @param utilisateurJeuDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<JeuUtilisateurDTO> ajoutJeu(@RequestBody JeuUtilisateurDTO utilisateurJeuDTO){
		logger.info("JeuUtilisateurController : ajoutJeu");
		return ResponseEntity.ok(utilisateurJeuMapperImpl.mapToDto(utilisateurJeuService.ajoutJeuListeUtilisateur(utilisateurJeuMapperImpl.mapToEntity(utilisateurJeuDTO))));
	}
	
	/**
	 * Retourne une liste des jeux que l'utilisateur a ajouté à celle ci.
	 * @param id
	 * @return ResponseEntity<List<JeuUtilisateurDTO>>
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<List<JeuUtilisateurDTO>> getListeJeuxAjoutes(@PathVariable Long id){
		logger.info("JeuUtilisateurController : getListeJeuxAjoutes");
		List<JeuUtilisateurDTO> listeJeuxUtilisateurDTO = utilisateurJeuService.getJeuxByUtilisateurId(id);
		
		if(listeJeuxUtilisateurDTO.isEmpty()) {
			 logger.info("Aucun jeu trouvé.");
			 return ResponseEntity.noContent().build();
		}
		logger.info("Nombre de jeux trouvés: {}", listeJeuxUtilisateurDTO.size());
		return ResponseEntity.ok(listeJeuxUtilisateurDTO);
	}
}