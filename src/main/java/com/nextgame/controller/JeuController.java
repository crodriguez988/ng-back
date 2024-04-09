package com.nextgame.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextgame.dtos.JeuDTO;
import com.nextgame.mappers.JeuMapperImpl;
import com.nextgame.services.JeuService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/jeux")
public class JeuController {
	
	@Autowired
	JeuService jeuService;
	
	@Autowired
	JeuMapperImpl jeuMapperImpl;

	/**
	 * EndPoint GET : Retourne une liste de jeux
	 * @return List<JeuDTO>
	 */
	@GetMapping()
	public List<JeuDTO> getAllJeux (){
		List<JeuDTO> listeJeuxDto = new ArrayList<>();
		jeuService.getAll().forEach(jeu -> listeJeuxDto.add(jeuMapperImpl.mapToDto(jeu)));
		System.err.println(listeJeuxDto);
		
		return listeJeuxDto;
	}
}
