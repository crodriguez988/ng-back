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
import com.nextgame.entities.StudioDev;

@SpringBootTest
@AutoConfigureMockMvc
public class StudioDevControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	 @Autowired
	 private ObjectMapper objectMapper;
	 
	 @Test
	 public void addStudioDevTest() throws Exception {
		 StudioDev studioDev = new StudioDev();
		 studioDev.setNom("Larrian Studios");
		 
	    mockMvc.perform(MockMvcRequestBuilders.post("/studiosDev")
			.accept(MediaType.APPLICATION_JSON)
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(studioDev)))
	    .andExpect(MockMvcResultMatchers.status().isOk());
	 }
	 
	 @Test
	 public void getStudioDevByIdTest() throws Exception {
		 int id = 1;
		 
		 mockMvc.perform(MockMvcRequestBuilders.get("/studiosDev/{id}", id)
			.contentType(MediaType.APPLICATION_JSON))
		 	.andExpect(MockMvcResultMatchers.status().isOk());
	 }
 
	 @Test
	 public void getAllStudiosDevTest() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/studiosDev")
			 .accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
		 .andExpect(MockMvcResultMatchers.status().isOk());
	 }
	 
	 @Test
	 public void updateStudioDevTest() throws Exception {
		 int id = 1;
		 StudioDev studioDev = new StudioDev ();
		 studioDev.setNom("Larian Studios");
		 
		 mockMvc.perform(MockMvcRequestBuilders.put("/studiosDev/{id}", id)
			 .accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
		 	.content(objectMapper.writeValueAsString(studioDev)))
		 .andExpect(MockMvcResultMatchers.status().isOk());
	 }
	 
	//@Test
	public void deleteStudioDevTest() throws Exception {
		int id = 1;
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/studiosDev/{id}", id)
	    .contentType(MediaType.APPLICATION_JSON))
	    .andExpect(MockMvcResultMatchers.status().isOk());
	 }	

}
