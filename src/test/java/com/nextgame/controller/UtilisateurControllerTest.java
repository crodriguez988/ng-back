package com.nextgame.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextgame.dtos.UtilisateurDTO;
import com.nextgame.entities.Utilisateur;
import com.nextgame.mappers.UtilisateurMapperImpl;
import com.nextgame.services.MyUserDetailsService;
import com.nextgame.services.UtilisateurService;
import com.nextgame.utils.JwtService;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilisateurControllerTest {
	
	@MockBean
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtService jwtService;
	
	private String jwtToken;
	
	@MockBean
	private UtilisateurService utilisateurService;
	
	@MockBean
    private UtilisateurMapperImpl utilisateurMapperImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	private String uri = "/utilisateurs";
	
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
	public void getAllUtilisateursTest() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail("user1@gmail.com");
		utilisateur.setMotDePasse("mdrUser1");
		
		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setEmail("user2@gmail.com");
		utilisateur.setMotDePasse("mdrUser2");
		
		Utilisateur utilisateur3 = new Utilisateur();
		utilisateur3.setEmail("user3@gmail.com");
		utilisateur.setMotDePasse("mdrUser3");
		
		List<Utilisateur> listeUtilisateurs = List.of(utilisateur, utilisateur2, utilisateur3);
		
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		utilisateurDTO.setEmail("user1@gmail.com");
		utilisateurDTO.setMotDePasse("mdrUser1");
		
		UtilisateurDTO utilisateurDTO2 = new UtilisateurDTO();
		utilisateurDTO2.setEmail("user2@gmail.com");
		utilisateurDTO2.setMotDePasse("mdrUser2");
		
		UtilisateurDTO utilisateurDTO3 = new UtilisateurDTO();
		utilisateurDTO3.setEmail("user3@gmail.com");
		utilisateurDTO3.setMotDePasse("mdrUser3");
		
		//Mock du service et des dto
		when(utilisateurService.getAll()).thenReturn(listeUtilisateurs);
 		when(utilisateurMapperImpl.mapToDto(utilisateur)).thenReturn(utilisateurDTO);
 		when(utilisateurMapperImpl.mapToDto(utilisateur2)).thenReturn(utilisateurDTO2);
 		when(utilisateurMapperImpl.mapToDto(utilisateur3)).thenReturn(utilisateurDTO3);
 		
 		//Mock de la requete GET
 		mockMvc.perform(MockMvcRequestBuilders.get(uri)
 				.header("Authorization", "Bearer " + jwtToken)
 				.accept(MediaType.APPLICATION_JSON)
 				.contentType(MediaType.APPLICATION_JSON))
 			.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
 			.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("user1@gmail.com")) 
 			.andExpect(MockMvcResultMatchers.jsonPath("$[0].motDePasse").value("mdrUser1"))
 			.andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("user2@gmail.com")) 
 			.andExpect(MockMvcResultMatchers.jsonPath("$[1].motDePasse").value("mdrUser2"))
 			.andExpect(MockMvcResultMatchers.jsonPath("$[2].email").value("user3@gmail.com"))
 			.andExpect(MockMvcResultMatchers.jsonPath("$[2].motDePasse").value("mdrUser3"));
	}
	
	@Test
	public void getUtilisateurByIdTest() throws Exception {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(1L);
		utilisateur.setEmail("user1@gmail.com");
		utilisateur.setMotDePasse("mdrUser1");
		
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		utilisateurDTO.setId(1L);
		utilisateurDTO.setEmail("user1@gmail.com");
		utilisateurDTO.setMotDePasse("mdrUser1");
		
		//Mock du service et du dto
		when(utilisateurService.existById(1L)).thenReturn(true);
		when(utilisateurService.getById(1L)).thenReturn(utilisateur);
		when(utilisateurMapperImpl.mapToDto(utilisateur)).thenReturn(utilisateurDTO);
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri + "/{id}", 1L)
 				.header("Authorization", "Bearer " + jwtToken)
 				.accept(MediaType.APPLICATION_JSON)
 				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
 			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)) 
 			.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("user1@gmail.com"))
 			.andExpect(MockMvcResultMatchers.jsonPath("$.motDePasse").value("mdrUser1"));
 			
	}
	
	@Test
	public void postTest () throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail("user4@gmail.com");
		utilisateur.setMotDePasse("mdrUser4");
		
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		utilisateurDTO.setEmail("user4@gmail.com");
		utilisateurDTO.setMotDePasse("mdrUser4");
		
		when(utilisateurMapperImpl.mapToDto(utilisateur)).thenReturn(utilisateurDTO);
		when(utilisateurService.save(any(Utilisateur.class))).thenReturn(utilisateur);
		
		//Mock pour la requete POST
		mockMvc.perform(MockMvcRequestBuilders.post(uri + "/inscription")
	    		.header("Authorization", "Bearer " + jwtToken)
		    	.accept(MediaType.APPLICATION_JSON)
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(utilisateur)))
		    .andExpect(MockMvcResultMatchers.status().isCreated());
		    //.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("user4@gmail.com"))
		    //.andExpect(MockMvcResultMatchers.jsonPath("$.motDePasse").value("mdrUser4"));
	}
	
	@Test
	public void updateTest() throws JsonProcessingException, Exception {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(4L);
		utilisateur.setEmail("user4@gmail.com");
		
		Utilisateur utilisateurUpdated = new Utilisateur();
		utilisateurUpdated.setId(4L);
		utilisateurUpdated.setEmail("userUpdated@gmail.com");
		
		UtilisateurDTO utilisateurUpdatedDTO = new UtilisateurDTO();
		utilisateurUpdatedDTO.setId(4L);
		utilisateurUpdatedDTO.setEmail("userUpdated@gmail.com");
		
		//Mock des appels des services et du mapper to DTO
		when(utilisateurService.existById(4L)).thenReturn(true);
		when(utilisateurService.getById(4L)).thenReturn(utilisateur);
		when(utilisateurService.update(utilisateur)).thenReturn(utilisateurUpdated);
		when(utilisateurMapperImpl.mapToDto(utilisateurUpdated)).thenReturn(utilisateurUpdatedDTO);
	
		//Mock pour la requete PUT
		mockMvc.perform(MockMvcRequestBuilders.put(uri + "/{id}", 4L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(utilisateurUpdatedDTO))
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$.id").value(4L))
			.andExpect(jsonPath("$.email").value("userUpdated@gmail.com"));
	}
	
	@Test
	public void delteTest() throws Exception {
		// On simule l'existance de la ressource pour ne pas avoir une erreur 404
		when(utilisateurService.existById(4L)).thenReturn(true);
	    doNothing().when(utilisateurService).delete(4L);
		
	    //Mock pour la requete DELETE
		mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/{id}", 4L)
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", "Bearer " + jwtToken)
			.accept(MediaType.APPLICATION_JSON))
    	.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}