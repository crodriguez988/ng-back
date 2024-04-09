package com.nextgame.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nextgame.mappers.EditeurMapperImpl;
import com.nextgame.mappers.GenreMapperImpl;
import com.nextgame.mappers.JeuMapperImpl;
import com.nextgame.mappers.PlateformeMapperImpl;
import com.nextgame.mappers.StudioDevMapperImpl;

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
	public StudioDevMapperImpl studioDevMapperImpl() {
		return new StudioDevMapperImpl();
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
}