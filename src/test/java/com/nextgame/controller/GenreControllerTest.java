package com.nextgame.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextgame.entities.Genre;

@SpringBootTest
@AutoConfigureMockMvc
/**
 * Tests pour le contolleur Genre)
 */
public class GenreControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	 @Autowired
	 private ObjectMapper objectMapper;
	 
	 @Test
	 public void addGenreTest() throws Exception {
		 Genre genre = new Genre();
		 genre.setLibelle("Action-RPG");
		 
	    mockMvc.perform(MockMvcRequestBuilders.post("/genres")
			.accept(MediaType.APPLICATION_JSON)
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(genre)))
	    .andExpect(MockMvcResultMatchers.status().isOk());
	 }

	 @Test
	 public void getGenreByIdTest() throws Exception {
		 int id = 1;
		 
		 mockMvc.perform(MockMvcRequestBuilders.get("/genres/{id}", id)
			.contentType(MediaType.APPLICATION_JSON))
		 	.andExpect(MockMvcResultMatchers.status().isOk());
	 }
 
	 @Test
	 public void getAllGenresTest() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/genres")
			 .accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
		 .andExpect(MockMvcResultMatchers.status().isOk());
	 }
	 
	 @Test
	 public void updateGenreTest() throws Exception {
		 int id = 1;
		 Genre updateGenre = new Genre();
		 updateGenre.setLibelle("Action-Horror");
		 
		 mockMvc.perform(MockMvcRequestBuilders.put("/genres/{id}", id)
			 .accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
		 	.content(objectMapper.writeValueAsString(updateGenre)))
		 .andExpect(MockMvcResultMatchers.status().isOk());
	 }
	 
	//@Test
	public void deleteGenreTest() throws Exception {
		int id = 1;
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/genres/{id}", id)
	    .contentType(MediaType.APPLICATION_JSON))
	    .andExpect(MockMvcResultMatchers.status().isOk());
	 }							

}