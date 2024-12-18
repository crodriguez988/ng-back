package com.nextgame.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextgame.dtos.PlateformeDTO;
import com.nextgame.entities.Plateforme;
import com.nextgame.entities.Utilisateur;
import com.nextgame.mappers.PlateformeMapperImpl;
import com.nextgame.services.MyUserDetailsService;
import com.nextgame.services.PlateformeService;
import com.nextgame.utils.JwtService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlateformeControllerTest {

	@MockBean
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtService jwtService;
	
	private String jwtToken;
	
	@MockBean
	private PlateformeService plateformeService;
	
	@MockBean
    private PlateformeMapperImpl plateformeMapperImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	private String uri = "/plateformes";
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	public void generationJWT() {
		Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setEmail("test@gmail.com");
	    Mockito.when(myUserDetailsService.loadUserByUsername("test@gmail.com"))
	           .thenReturn(new User("test@gmail.com", "password", new ArrayList<>()));
	    jwtToken = jwtService.genererToken(utilisateur);
	}

	@Test
	public void getAllPlateformesTest() throws Exception {
		Plateforme plateforme1 = new Plateforme(1L, "Play Station 5", "PS5");
		
		Plateforme plateforme2 = new Plateforme(2L, "PC", "");
		
		Plateforme plateforme3 = new Plateforme(3L, "Xbox one X", "");
		
 		List<Plateforme> listePlateformes = List.of(plateforme1, plateforme2, plateforme3);
	
 		//Mock du service et du mapper to DTO
 		when(plateformeService.getAll()).thenReturn(listePlateformes);
 		when(plateformeMapperImpl.mapToDto(plateforme1)).thenReturn(new PlateformeDTO(1L, "Play Station 5", "PS5"));
 		when(plateformeMapperImpl.mapToDto(plateforme2)).thenReturn(new PlateformeDTO(2L, "PC", ""));
 		when(plateformeMapperImpl.mapToDto(plateforme3)).thenReturn(new PlateformeDTO(3L, "Xbox one X", ""));
 		
 		//Mock pour la requete GET
 		mockMvc.perform(MockMvcRequestBuilders.get(uri)
 				 .accept(MediaType.APPLICATION_JSON)
 				 .contentType(MediaType.APPLICATION_JSON))
 			 .andExpect(MockMvcResultMatchers.status().isOk())
 			 .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L)) 
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Play Station 5"))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[0].abreviation").value("PS5"))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L)) 
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("PC"))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[1].abreviation").value(""))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3L))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[2].nom").value("Xbox one X"))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[2].abreviation").value(""));
	}
	
	@Test
	public void getPlateformeByIdTest () throws Exception {
		Plateforme plateforme = new Plateforme(3L, "Xbox one X", "");
		
		//Mock des appels des services et du mapper to DTO
		when(plateformeService.existById(3L)).thenReturn(true);
		when(plateformeService.getById(3L)).thenReturn(plateforme);
		when(plateformeMapperImpl.mapToDto(plateforme)).thenReturn(new PlateformeDTO(3L, "Xbox one X", ""));
		
		//Mock pour la requete GET
		mockMvc.perform(MockMvcRequestBuilders.get(uri + "/{id}", 3L)
				.accept(MediaType.APPLICATION_JSON))
	 		.andExpect(status().isOk())
	 		.andExpect(jsonPath("$.id").value(3L))
	 		.andExpect(jsonPath("$.nom").value("Xbox one X"));
	}
	
	@Test
	public void postTest() throws Exception {
		Plateforme plateforme = new Plateforme();
		plateforme.setNom("PS6");
		
		//Mock pour la requete POST
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
	    		.header("Authorization", "Bearer " + jwtToken)
		    	.accept(MediaType.APPLICATION_JSON)
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(plateforme)))
		    .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void updateTest() throws Exception {
		Plateforme plateforme = new Plateforme(4L, "PS6", "");
		Plateforme plateformeUpdated = new Plateforme(4L, "Play Station 6", "PS6");
		
		PlateformeDTO plateformeDTO = new PlateformeDTO(4L, "Play Station 6", "PS6");
		
		//Mock des appels des services et du mapper to DTO
		when(plateformeService.existById(4L)).thenReturn(true);
		when(plateformeService.getById(4L)).thenReturn(plateforme);
		when(plateformeService.update(plateforme)).thenReturn(plateformeUpdated);
		when(plateformeMapperImpl.mapToDto(plateformeUpdated)).thenReturn(plateformeDTO);
	
		//Mock pour la requete PUT
		mockMvc.perform(MockMvcRequestBuilders.put(uri + "/{id}", 4L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(plateformeDTO))
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$.id").value(4L))
			.andExpect(jsonPath("$.nom").value("Play Station 6"))
			.andExpect(jsonPath("$.abreviation").value("PS6"));
	}
	
	@Test
	public void deleteTest() throws Exception {
		// On simule l'existance de la ressource pour ne pas avoir une erreur 404
		when(plateformeService.existById(4L)).thenReturn(true);
	    doNothing().when(plateformeService).delete(4L);
		
	    //Mock pour la requete DELETE
		mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/{id}", 4L)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON))
	    	.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
