package com.nextgame.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.nextgame.dtos.DeveloppeurDTO;
import com.nextgame.entities.Developpeur;
import com.nextgame.entities.Utilisateur;
import com.nextgame.mappers.DeveloppeurMapperImpl;
import com.nextgame.services.DeveloppeurService;
import com.nextgame.services.MyUserDetailsService;
import com.nextgame.utils.JwtService;

@SpringBootTest
@AutoConfigureMockMvc
public class DeveloppeurControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	 @Autowired
	 private ObjectMapper objectMapper;
	 
	 private String uri = "/developpeurs";
	 
	 @Autowired
	 private JwtService jwtService;
	 
	 private String jwtToken;
	 
	 @MockBean
	 private MyUserDetailsService myUserDetailsService;
	 
	 @MockBean
	 private DeveloppeurService developpeurService;
	 
	 @MockBean
     private DeveloppeurMapperImpl developpeurMapperImpl;
	 

	 @BeforeEach
	 public void generationJWT() {
		 Utilisateur utilisateur = new Utilisateur();
		    utilisateur.setEmail("test@gmail.com");
		    Mockito.when(myUserDetailsService.loadUserByUsername("test@gmail.com"))
		           .thenReturn(new User("test@gmail.com", "password", new ArrayList<>()));
		    jwtToken = jwtService.genererToken(utilisateur);
	 }
	 
	 @Test
	 public void postDeveloppeurTest() throws Exception {
		 Developpeur developpeur = new Developpeur();
		 developpeur.setNom("Larrian Studios");
		 
	    mockMvc.perform(MockMvcRequestBuilders.post(uri)
    		.header("Authorization", "Bearer " + jwtToken)
	    	.accept(MediaType.APPLICATION_JSON)
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(developpeur)))
	    .andExpect(MockMvcResultMatchers.status().isCreated());
	 }
	 
	 @Test
	 public void getDeveloppeurByIdTest() throws Exception {
		 Developpeur developpeur = new Developpeur();
		 developpeur.setId(1L);
		 developpeur.setNom("Larrian Studios");

		 DeveloppeurDTO developpeurDTO = new DeveloppeurDTO();
		 developpeurDTO.setId(1L);
		 developpeurDTO.setNom("Larrian Studios");
		 
		 when(developpeurService.existById(1L)).thenReturn(true);
		 when(developpeurService.getById(1L)).thenReturn(developpeur);
		 
		 //Mock du mappeur to DTO
		 when(developpeurMapperImpl.mapToDto(any())).thenReturn(developpeurDTO);
		 
		 mockMvc.perform(MockMvcRequestBuilders.get(uri + "/{id}", 1L)
			 .accept(MediaType.APPLICATION_JSON))
         	 .andExpect(status().isOk())
         	 .andExpect(jsonPath("$.id").value(1L))
         	 .andExpect(jsonPath("$.nom").value("Larrian Studios"));
	 }
 
	 @Test
	 public void  getDeveloppeursTest() throws Exception {
		 Developpeur developpeur1 = new Developpeur();
		 developpeur1.setId(1L);
		 developpeur1.setNom("Larrian Studios");
		 
		 Developpeur developpeur2 = new Developpeur();
		 developpeur2.setId(2L);
		 developpeur2.setNom("Santa Monica Studio");
		 
		 Developpeur developpeur3 = new Developpeur();
		 developpeur3.setId(3L);
		 developpeur3.setNom("CD Red Projekt");
		 
		 List<Developpeur> listeDeveloppeurs = List.of(developpeur1, developpeur2, developpeur3);
		 
		 // Mock du service
		 when(developpeurService.getAll()).thenReturn(listeDeveloppeurs);
		    
		 //Mock des mappeurs to DTO
		 when(developpeurMapperImpl.mapToDto(developpeur1)).thenReturn(new DeveloppeurDTO(1L, "Larrian Studios"));
		 when(developpeurMapperImpl.mapToDto(developpeur2)).thenReturn(new DeveloppeurDTO(2L, "Santa Monica Studio"));
		 when(developpeurMapperImpl.mapToDto(developpeur3)).thenReturn(new DeveloppeurDTO(3L, "CD Projekt Red"));
		 
		 mockMvc.perform(MockMvcRequestBuilders.get(uri)
			 .accept(MediaType.APPLICATION_JSON)
			 .contentType(MediaType.APPLICATION_JSON))
		 .andExpect(MockMvcResultMatchers.status().isOk())
		 .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L)) 
		 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Larrian Studios"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L)) 
		 .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Santa Monica Studio"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3L))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[2].nom").value("CD Projekt Red"));
	 }
	 
	@Test
	public void updateDeveloppeurTest() throws Exception {
		Developpeur developpeurExistant = new Developpeur ();
		developpeurExistant.setId(1L);
		developpeurExistant.setNom("Larian Studios");
		
		Developpeur developpeurUpdated = new Developpeur ();
		developpeurUpdated.setId(1L);
		developpeurUpdated.setNom("Larianos Studios");
		
		DeveloppeurDTO developpeurDTO = new DeveloppeurDTO(1L, "Larianos Studios");
		
		// Mock des appels de service et mapper
	    when(developpeurService.existById(1L)).thenReturn(true);
	    when(developpeurService.getById(1L)).thenReturn(developpeurExistant);
	    when(developpeurService.update(any(Developpeur.class))).thenReturn(developpeurUpdated);
	    when(developpeurMapperImpl.mapToDto(developpeurUpdated)).thenReturn(developpeurDTO);
		 
		mockMvc.perform(MockMvcRequestBuilders.put(uri + "/{id}", 1L)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(developpeurDTO))
			.header("Authorization", "Bearer " + jwtToken)
			.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$.id").value(1L))
		.andExpect(jsonPath("$.nom").value("Larianos Studios"));
	}
 
	@Test
	public void deleteStudioDevTest() throws Exception {
		
		// On simule l'existance de la ressource pour ne pas avoir une erreur 404
		when(developpeurService.existById(1L)).thenReturn(true);
	    doNothing().when(developpeurService).delete(1L);
		
		mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON))
	    	.andExpect(MockMvcResultMatchers.status().isNoContent());
	 }	

}
