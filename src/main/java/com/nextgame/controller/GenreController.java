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

import com.nextgame.dtos.GenreDTO;
import com.nextgame.entities.Genre;
import com.nextgame.mappers.GenreMapperImpl;
import com.nextgame.services.GenreService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/genres")
public class GenreController {
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	GenreMapperImpl genreMapperImpl;
	
	@GetMapping()
	public List<GenreDTO> getAllGenres (){
		System.err.println("GenreController - getAll()");
		List<GenreDTO> listGenresDto = new ArrayList<>();
		genreService.getAll().forEach(genre -> listGenresDto.add(genreMapperImpl.mapToDto(genre)));
		System.err.println(listGenresDto);
		return listGenresDto;
	}
	
	@GetMapping(path = "/{id}")
	public GenreDTO getById(@PathVariable Long id) {
		System.err.println("GenreController - getById()");
		//TODO: Gerer si l'id n'existe pas
		GenreDTO genreDto = genreMapperImpl.mapToDto(genreService.getById(id));
		return genreDto;
	}
	
	@PostMapping
	public GenreDTO post(@RequestBody GenreDTO newGenre) {
		return genreMapperImpl.mapToDto(genreService.save(genreMapperImpl.mapToEntity(newGenre)));
	}
	
	@PutMapping(path = "/{id}")
	public GenreDTO update (@RequestBody(required = true) GenreDTO genretDTO, @PathVariable long id) {
		//TODO Vérification que l'id existe
		Genre genre = genreService.getById(id);
		genre.setLibelle(genretDTO.getLibelle());
		return genreMapperImpl.mapToDto(genreService.update(genreService.update(genreMapperImpl.mapToEntity(genretDTO))));
	}
	
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Long id) {
		System.err.println("GenreController - delete()");
		//TODO: Vérifier si l'id existe
		genreService.delete(id);
	}
}