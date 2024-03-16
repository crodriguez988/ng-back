package com.nextgame.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextgame.dtos.GenreDTO;
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
		System.err.println("GenreController - GetAll()");
		List<GenreDTO> res = new ArrayList<>();
		genreService.getAll().forEach(genre -> res.add(genreMapperImpl.mapToDto(genre)));
		System.err.println(res);
		return res;
	}
	
	@GetMapping("/test1")
	public String printHello () {
		return "Hello world";
	}
}