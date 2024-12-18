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
import com.nextgame.dtos.EditeurDTO;
import com.nextgame.entities.Editeur;
import com.nextgame.entities.Utilisateur;
import com.nextgame.mappers.EditeurMapperImpl;
import com.nextgame.services.EditeurService;
import com.nextgame.services.MyUserDetailsService;
import com.nextgame.utils.JwtService;

@SpringBootTest
@AutoConfigureMockMvc
public class EditeurControllerTest {
	
	@MockBean
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtService jwtService;
	
	private String jwtToken;
	
	@MockBean
	private EditeurService editeurService;
	
	@MockBean
    private EditeurMapperImpl editeurMapperImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	private String uri = "/editeurs";
	
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
	public void getAllEditeursTest() throws Exception {
		Editeur editeur1 = new Editeur(1L, "Bandai Namco");
		
		Editeur editeur2 = new Editeur(2L, "Capcom");
		
		Editeur editeur3 = new Editeur(3L, "Nintendo");
		
 		List<Editeur> listeEditeurs = List.of(editeur1, editeur2, editeur3);
	
 		//Mock du service et du mapper to DTO
 		when(editeurService.getAll()).thenReturn(listeEditeurs);
 		when(editeurMapperImpl.mapToDto(editeur1)).thenReturn(new EditeurDTO(1L, "Bandai Namco"));
 		when(editeurMapperImpl.mapToDto(editeur2)).thenReturn(new EditeurDTO(2L, "Capcom"));
 		when(editeurMapperImpl.mapToDto(editeur3)).thenReturn(new EditeurDTO(3L, "Nintendo"));
 		
 		//Mock pour la requete GET
 		mockMvc.perform(MockMvcRequestBuilders.get(uri)
 				 .accept(MediaType.APPLICATION_JSON)
 				 .contentType(MediaType.APPLICATION_JSON))
 			 .andExpect(MockMvcResultMatchers.status().isOk())
 			 .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L)) 
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Bandai Namco"))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L)) 
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Capcom"))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3L))
 			 .andExpect(MockMvcResultMatchers.jsonPath("$[2].nom").value("Nintendo"));
	}
	
	@Test
	public void getEditeurByIdTest () throws Exception {
		Editeur editeur = new Editeur(3L, "Bandai Namco");
		
		//Mock des appels des services et du mapper to DTO
		when(editeurService.existById(3L)).thenReturn(true);
		when(editeurService.getById(3L)).thenReturn(editeur);
		when(editeurMapperImpl.mapToDto(editeur)).thenReturn(new EditeurDTO(3L, "Bandai Namco"));
		
		//Mock pour la requete GET
		mockMvc.perform(MockMvcRequestBuilders.get(uri + "/{id}", 3L)
				.accept(MediaType.APPLICATION_JSON))
	 		.andExpect(status().isOk())
	 		.andExpect(jsonPath("$.id").value(3L))
	 		.andExpect(jsonPath("$.nom").value("Bandai Namco"));
	}
	
	@Test
	public void postTest() throws Exception {
		Editeur editeur = new Editeur();
		editeur.setNom("Betesda");
		
		//Mock pour la requete POST
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
	    		.header("Authorization", "Bearer " + jwtToken)
		    	.accept(MediaType.APPLICATION_JSON)
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(editeur)))
		    .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void updateTest() throws Exception {
		Editeur editeur = new Editeur(4L, "Betesda");
		Editeur updatedEditeur = new Editeur(4L, "Bethesda");
		
		EditeurDTO editeurDTO = new EditeurDTO(4L, "Bethesda");
		
		//Mock des appels des services et du mapper to DTO
		when(editeurService.existById(4L)).thenReturn(true);
		when(editeurService.getById(4L)).thenReturn(editeur);
		when(editeurService.update(editeur)).thenReturn(updatedEditeur);
		when(editeurMapperImpl.mapToDto(updatedEditeur)).thenReturn(editeurDTO);
	
		//Mock pour la requete PUT
		mockMvc.perform(MockMvcRequestBuilders.put(uri + "/{id}", 4L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(editeurDTO))
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$.id").value(4L))
			.andExpect(jsonPath("$.nom").value("Bethesda"));
	}
	
	@Test
	public void deleteTest() throws Exception {
		// On simule l'existance de la ressource pour ne pas avoir une erreur 404
		when(editeurService.existById(3L)).thenReturn(true);
	    doNothing().when(editeurService).delete(3L);
		
	    //Mock pour la requete DELETE
		mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/{id}", 3L)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON))
	    	.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
