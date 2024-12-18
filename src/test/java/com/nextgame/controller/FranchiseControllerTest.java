package com.nextgame.controller;

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
import com.nextgame.dtos.FranchiseDTO;
import com.nextgame.entities.Franchise;
import com.nextgame.entities.Utilisateur;
import com.nextgame.mappers.FranchiseMapperImpl;
import com.nextgame.services.FranchiseService;
import com.nextgame.services.MyUserDetailsService;
import com.nextgame.utils.JwtService;

@SpringBootTest
@AutoConfigureMockMvc
public class FranchiseControllerTest {
	@MockBean
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtService jwtService;
	
	private String jwtToken;
	
	@MockBean
	private FranchiseService franchiseService;
	
	@MockBean
    private FranchiseMapperImpl franchiseMapperImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	private String uri = "/franchises";
	
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
	public void getAllFranchisesTest() throws Exception {
				
		Franchise franchise1 = new Franchise();
		franchise1.setId(1L);
		franchise1.setNom("Grand Theft Auto");
		franchise1.setAbreviation("GTA");
		
		Franchise franchise2 = new Franchise();
		franchise2.setId(2L);
		franchise2.setNom("Pokemon");
		
		Franchise franchise3 = new Franchise();
		franchise3.setId(3L);
		franchise3.setNom("Dragon Age");
		
		FranchiseDTO franchiseDTO1 = new FranchiseDTO();
		franchiseDTO1.setId(1L);
		franchiseDTO1.setNom("Grand Theft Auto");
		franchiseDTO1.setAbreviation("GTA");
		
		FranchiseDTO franchiseDTO2 = new FranchiseDTO();
		franchiseDTO2.setId(2L);
		franchiseDTO2.setNom("Pokemon");
		
		FranchiseDTO franchiseDTO3 = new FranchiseDTO();
		franchiseDTO3.setId(3L);
		franchiseDTO3.setNom("Dragon Age");
		
 		List<Franchise> listeFranchises = List.of(franchise1, franchise2, franchise3);
	
 		//Mock du service et du mapper to DTO
 		when(franchiseService.getAll()).thenReturn(listeFranchises);
 		when(franchiseMapperImpl.mapToDto(franchise1)).thenReturn(franchiseDTO1);
 		when(franchiseMapperImpl.mapToDto(franchise2)).thenReturn(franchiseDTO2);
 		when(franchiseMapperImpl.mapToDto(franchise3)).thenReturn(franchiseDTO3);
 		
 		//Mock pour la requete GET
 		mockMvc.perform(MockMvcRequestBuilders.get(uri)
 				 .accept(MediaType.APPLICATION_JSON)
 				 .contentType(MediaType.APPLICATION_JSON))
 			 .andExpect(MockMvcResultMatchers.status().isOk())
 			 .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L)) 
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Grand Theft Auto"))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[0].abreviation").value("GTA"))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L)) 
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Pokemon"))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3L))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[2].nom").value("Dragon Age"));
	}
	
	@Test
	public void getEditeurByIdTest () throws Exception {
		Franchise franchise = new Franchise();
		franchise.setId(3L);
		franchise.setNom("Dragon Age");
		
		FranchiseDTO franchiseDTO = new FranchiseDTO();
		franchiseDTO.setId(3L);
		franchiseDTO.setNom("Dragon Age");
		
		//Mock des appels des services et du mapper to DTO
		when(franchiseService.existById(3L)).thenReturn(true);
		when(franchiseService.getById(3L)).thenReturn(franchise);
		when(franchiseMapperImpl.mapToDto(franchise)).thenReturn(franchiseDTO);
		
		//Mock pour la requete GET
		mockMvc.perform(MockMvcRequestBuilders.get(uri + "/{id}", 3L)
				.accept(MediaType.APPLICATION_JSON))
	 		.andExpect(status().isOk())
	 		.andExpect(jsonPath("$.id").value(3L))
	 		.andExpect(jsonPath("$.nom").value("Dragon Age"));
	}
	
	@Test
	public void postTest() throws Exception {
		Franchise franchise = new Franchise();
		franchise.setNom("The legend of zelda");
		franchise.setAbreviation("Zelda");
		
		//Mock pour la requete POST
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
	    		.header("Authorization", "Bearer " + jwtToken)
		    	.accept(MediaType.APPLICATION_JSON)
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(franchise)))
		    .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void updateTest() throws Exception {
		Franchise franchise = new Franchise();
		franchise.setId(3L);
		franchise.setNom("Dragon Age");
		
		Franchise franchiseUpdated = new Franchise();
		franchiseUpdated.setNom("Dragon's Age");
		
		FranchiseDTO franchiseDTO = new FranchiseDTO();
		franchiseDTO.setId(3L);
		franchiseDTO.setNom("Dragon's Age");
		
		//Mock des appels des services et du mapper to DTO
		when(franchiseService.existById(3L)).thenReturn(true);
		when(franchiseService.getById(3L)).thenReturn(franchise);
		when(franchiseService.update(franchise)).thenReturn(franchiseUpdated);
		when(franchiseMapperImpl.mapToDto(franchiseUpdated)).thenReturn(franchiseDTO);
	
		//Mock pour la requete PUT
		mockMvc.perform(MockMvcRequestBuilders.put(uri + "/{id}", 3L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(franchiseDTO))
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$.id").value(3L))
			.andExpect(jsonPath("$.nom").value("Dragon's Age"));
	}
	
	@Test
	public void deleteTest() throws Exception {
		// On simule l'existance de la ressource pour ne pas avoir une erreur 404
		when(franchiseService.existById(3L)).thenReturn(true);
	    doNothing().when(franchiseService).delete(3L);
		
	    //Mock pour la requete DELETE
		mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/{id}", 3L)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON))
	    	.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
