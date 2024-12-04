package com.nextgame.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nextgame.mappers.EditeurMapperImpl;
import com.nextgame.mappers.FranchiseMapperImpl;
import com.nextgame.mappers.GenreMapperImpl;
import com.nextgame.mappers.JeuMapperImpl;
import com.nextgame.mappers.PlateformeMapperImpl;
import com.nextgame.mappers.JeuUtilisateurMapperImpl;
import com.nextgame.mappers.UtilisateurMapperImpl;
import com.nextgame.mappers.DeveloppeurMapperImpl;

/**
 * Cette classe permet d'initialiser les beans pour tous les mappers utilisés
 */
@Configuration
public class MapperInit {

	@Bean
	public GenreMapperImpl genreMapperImpl() {
		return new GenreMapperImpl();
	}
	
	@Bean
	public DeveloppeurMapperImpl developpeurMapperImpl() {
		return new DeveloppeurMapperImpl();
	}
	
	@Bean
	public PlateformeMapperImpl plateformeMapperImpl() {
		return new PlateformeMapperImpl();
	}
	
	@Bean
	public EditeurMapperImpl editeurMapperImpl() {
		return new EditeurMapperImpl();
	}
	
	@Bean
	public JeuMapperImpl jeuMapperImpl() {
		return new JeuMapperImpl();
	}
	
	@Bean
	public FranchiseMapperImpl franchiseMapperImpl() {
		return new FranchiseMapperImpl();
	}
	
	@Bean
	public UtilisateurMapperImpl utilisateurMapperImpl () {
		return new UtilisateurMapperImpl(); 
	}
	
	@Bean
	public JeuUtilisateurMapperImpl jeuUtilisateurMapperImpl() {
		return new JeuUtilisateurMapperImpl(); 
	}
}