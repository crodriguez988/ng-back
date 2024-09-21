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

import com.nextgame.dtos.GenreDTO;
import com.nextgame.entities.Genre;
import com.nextgame.mappers.GenreMapperImpl;
import com.nextgame.services.GenreService;

/**
 * Contrôleur REST exposant les endpoints d'un genre, récupération, ajout, modifications, etc.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/genres")
public class GenreController {
	
	@Autowired
	GenreService genreService;
	
	@Autowired GenreMapperImpl genreMapperImpl;
	
	private static final Logger logger = LogManager.getLogger(GenreController.class);
	
	
	/**
	 * Retourne une liste de genres.
	 * @return ResponseEntity<List<GenreDTO>>
	 */
	@GetMapping()
	public ResponseEntity<List<GenreDTO>> getAllGenres (){
		logger.info("GenreController - getAllGenres");
		List<GenreDTO> listeGenresDto = genreService.getAll()
				.stream().map(genreMapperImpl::mapToDto).collect(Collectors.toList());
		
		if (listeGenresDto.isEmpty()) {
	            logger.info("Aucun genre trouvé.");
	            return ResponseEntity.noContent().build();
        }
		logger.info("Genres trouvés : {}", listeGenresDto.size());
		return ResponseEntity.ok(listeGenresDto);
	}
	
	/**
	 * Retourne un genre en fonction de l'id passé dans le path.
	 * @param id
	 * @return ResponseEntity<GenreDTO> 
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
		logger.info("GenreController - getGenreById() - id : {}", id);
		// Vérifie que l'id existe
		if(genreService.existById(id)) {
			GenreDTO genreDTO = genreMapperImpl.mapToDto(genreService.getById(id));
			return ResponseEntity.ok(genreDTO);
		}
		else {
			logger.warn("Le développeur avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Crée un nouveau genre.
	 * @param newGenre
	 * @return ResponseEntity<GenreDTO>
	 */
	@PostMapping
	public ResponseEntity<GenreDTO> post(@RequestBody GenreDTO newGenre) {
		logger.info("GenreController - post");
		GenreDTO genreEnr = genreMapperImpl.mapToDto(genreService.save(genreMapperImpl.mapToEntity(newGenre)));
		return ResponseEntity.status(HttpStatus.CREATED).body(genreEnr);
	}
	
	/**
	 * Met à jour un genre en fonction de l'id dans le path.
	 * @param genretDTO
	 * @param id
	 * @return ResponseEntity<GenreDTO>
	 */
	@PutMapping(path = "/{id}")
	public ResponseEntity<GenreDTO> update (@RequestBody(required = true) GenreDTO genreDTO, @PathVariable long id) {
		logger.info("GenreController - update");
		// Vérifie que l'id existe
		if(genreService.existById(id)) {
			Genre genre = genreService.getById(id);
			genre.setLibelle(genreDTO.getLibelle());
			return ResponseEntity.ok(genreMapperImpl.mapToDto(genreService.update(genre)));
		}
		else {
			logger.warn("Le genre avec l'id {} n'a pas été trouvé.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Supprime un genre en fonction de l'id dans le path
	 * @param id
	 * @return ResponseEntity avec le statut HTTP approprié. 
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("GenreController - delete - id : {}", id);
		// Vérifie que l'id existe
		if (genreService.existById(id)) {
			genreService.delete(id);
		    logger.info("Le genre avec l'id {} a été supprimé.", id);
		    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
		    logger.warn("Le genre avec l'id {} n'a pas été trouvé.", id);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}