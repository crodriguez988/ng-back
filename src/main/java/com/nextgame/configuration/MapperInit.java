package com.nextgame.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nextgame.mappers.GenreMapperImpl;

/**
 * Cette classe permet d'initialiser les beans pour tous les mappers utilisés
 */
@Configuration
public class MapperInit {

	@Bean
	public GenreMapperImpl genreMapperImpl() {
		return new GenreMapperImpl();
	}
}