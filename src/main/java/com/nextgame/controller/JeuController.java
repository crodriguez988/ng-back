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

import com.nextgame.dtos.JeuDTO;
import com.nextgame.dtos.JeuxDetailsDTO;
import com.nextgame.entities.Jeu;
import com.nextgame.mappers.JeuMapperImpl;
import com.nextgame.services.JeuService;

/**
 * Contrôleur REST exposant les endpoints d'un jeu vidéo, récupération, ajout, modifications, etc.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/jeux")
public class JeuController {
	
	@Autowired
	JeuService jeuService;
	
	@Autowired
	JeuMapperImpl jeuMapperImpl;
	
	private static final Logger logger = LogManager.getLogger(JeuController.class);

	/**
	 * Retourne une liste avec tous les jeux.
	 * @return ResponseEntity<List<JeuDTO>>
	 */
	@GetMapping()
	public ResponseEntity<List<JeuDTO>> getAllJeux (){
		logger.info("JeuController : getAllJeux");
		List<JeuDTO> listeJeuxDto = jeuService.getAll()
	            .stream().map(jeuMapperImpl::mapToDto).collect(Collectors.toList());

		 if (listeJeuxDto.isEmpty()) {
	        logger.info("Aucun jeu trouvé.");
	        // Code 204 (No Content) si la liste est vide
	        return ResponseEntity.noContent().build();  
		 }

		 logger.info("Nombre de jeux trouvés: {}", listeJeuxDto.size());
		 return ResponseEntity.ok(listeJeuxDto);
	}
	
	/**
	 * Retourne un jeu en fonction de l'id passé dans le path.
	 * @param id
	 * @return ResponseEntity<JeuDTO>
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<JeuDTO> getJeuById(@PathVariable Long id) {
		logger.info("JeuController : getJeuById - id : {}", id);
		// Vérifie que l'id existe
		if(jeuService.existById(id)) {
			return ResponseEntity.ok(jeuMapperImpl.mapToDto(jeuService.getById(id)));
		}
		else {
			logger.warn("Le jeu  avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Retourne une liste de jeux en fonction de l'année passée en parametre.
	 * @param anneSortie 
	 * @return ResponseEntity<List<JeuDTO>>
	 */
    @GetMapping("/date/{annee}")
    public ResponseEntity<List<JeuDTO>> getJeuxByDate(@PathVariable("annee") int anneSortie) {
    	logger.info("JeuController : getJeuxByDate - annee : {}", anneSortie);
    	List<JeuDTO> listeJeuxDto = jeuService.getAllJeuxByAnneeSortie(anneSortie)
				.stream().map(jeuMapperImpl::mapToDto).collect(Collectors.toList());
    	
        if(listeJeuxDto.isEmpty()) {
        	logger.info("Aucun jeu trouvé.");
        	return ResponseEntity.noContent().build(); 
        }
        logger.info("Jeux trouvés : {}", listeJeuxDto.size());
        return ResponseEntity.ok(listeJeuxDto);
    }
    
    /**
     * Retourne une liste avec les jeux appartiennent au genre passé en param
     * @param idGenre
     * @return ResponseEntity<List<JeuDTO>>
     */
	@GetMapping(path = "/genre/{id}")
	public ResponseEntity<List<JeuDTO>> getJeuxByGenreId (@PathVariable("id") Long idGenre) {
		logger.info("JeuController : getJeuxByGenreId - idGenre : {}", idGenre);
    	List<JeuDTO> listeJeuxDto = jeuService.getAllJeuxByGenreId(idGenre)
				.stream().map(jeuMapperImpl::mapToDto).collect(Collectors.toList());
    	
        if(listeJeuxDto.isEmpty()) {
        	logger.info("Aucun jeu trouvé.");
        	return ResponseEntity.noContent().build(); 
        }
        logger.info("Jeux trouvés : {}", listeJeuxDto.size());
        return ResponseEntity.ok(listeJeuxDto);
	}
	
	/**
	 * Retourne une liste de jeux appartenant à la même franchise que l'id jeu passé en param
	 * @param idJeu
	 * @return ResponseEntity<List<JeuDTO>>
	 */
	@GetMapping(path = "/franchise/{id}")
    public ResponseEntity<List<JeuDTO>> getJeuxBySameFranchise(@PathVariable("id") Long idJeu) {
		logger.info("JeuController : getJeuxBySameFranchise - idGenre : {}", idJeu);
    	List<JeuDTO> listeJeuxDto = jeuService.getJeuxWithSameFranchise(idJeu)
				.stream().map(jeuMapperImpl::mapToDto).collect(Collectors.toList());
    	
        if(listeJeuxDto.isEmpty()) {
        	logger.info("Aucun jeu trouvé.");
        	return ResponseEntity.noContent().build(); 
        }
        logger.info("Jeux trouvés : {}", listeJeuxDto.size());
        return ResponseEntity.ok(listeJeuxDto);
	}
	
	/**
	 * Retourne une liste de jeux développés par le même dev
	 * @param idDeveloppeur
	 * @return ResponseEntity<List<JeuDTO>>
	 */
	@GetMapping(path = "/developpeur/{id}")
	public ResponseEntity<List<JeuDTO>> getJeuxByDeveloppeurId (@PathVariable("id") Long idDeveloppeur) {
		logger.info("JeuController : getJeuxByDeveloppeurId - idDeveloppeur : {}", idDeveloppeur);
    	List<JeuDTO> listeJeuxDto = jeuService.getAllJeuxByDeveloppeurId(idDeveloppeur)
				.stream().map(jeuMapperImpl::mapToDto).collect(Collectors.toList());
    	
        if(listeJeuxDto.isEmpty()) {
        	logger.info("Aucun jeu trouvé.");
        	return ResponseEntity.noContent().build(); 
        }
        logger.info("Jeux trouvés : {}", listeJeuxDto.size());
        return ResponseEntity.ok(listeJeuxDto);
	}
	
	/**
	 * Retourne une liste de jeu avec les données telles que : 
	 * - Nom
	 * - Développeur
	 * - Note (moyen totale du jeu)
	 * - EtatJeu 
	 * @return ResponseEntity<List<JeuxDetailsDTO>>
	 */
	@GetMapping(path = "detailJeux")
	public ResponseEntity<List<JeuxDetailsDTO>> getListeDetailsJeux(){
		logger.info("JeuController : getListeDetailsJeux ");
		List<JeuxDetailsDTO> jeuxDetails = jeuService.getListeJeuxDetails();
		return ResponseEntity.ok(jeuxDetails); 
	}
	
	/**
	 * Crée un novueau jeu.
	 * @param newJeu
	 * @return ResponseEntity<JeuDTO>
	 */
	@PostMapping
	public ResponseEntity<JeuDTO> post(@RequestBody JeuDTO newJeu) {
		logger.info("JeuController : post");
		return ResponseEntity.ok(jeuMapperImpl.mapToDto(jeuService.save(jeuMapperImpl.mapToEntity(newJeu))));
	}
	
	/**
	 * Met à jour un jeu en fonction de l'id dans le path.
	 * @param jeuDTO
	 * @param id
	 * @return ResponseEntity<JeuDTO>
	 */
	@PutMapping(path = "/{id}")
	public ResponseEntity<JeuDTO> update (@RequestBody JeuDTO jeuDTO, @PathVariable long id) {
		logger.info("JeuController : update");
	    // Vérifie que l'id existe 
	    if(jeuService.existById(id)) {
	    	Jeu jeu = jeuService.getById(id);
		    jeu.setNom(jeuDTO.getNom());
		    return ResponseEntity.ok(jeuMapperImpl.mapToDto(jeuService.update(jeu)));
	    }
	    else {
	    	logger.warn("Le jeu avec l'id {} n'a pas été trouvé.", id);
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}
	
	/**
	 * Supprime jeu en fonction de l'id dans le path.
	 * @param id
	 * @return ResponseEntity avec le statut HTTP approprié. 
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("JeuController : delete - id : {}", id);
		// Vérifie que l'id existe
		if(jeuService.existById(id)) {
			jeuService.delete(id);
			logger.info("Le jeu avec l'id {} a été supprimé.", id);
		    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			logger.warn("Le jeu avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}	
}