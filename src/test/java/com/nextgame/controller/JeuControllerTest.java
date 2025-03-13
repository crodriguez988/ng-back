package com.nextgame.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
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
import com.nextgame.dtos.JeuDTO;
import com.nextgame.dtos.PlateformeDTO;
import com.nextgame.entities.Genre;
import com.nextgame.entities.Jeu;
import com.nextgame.entities.Plateforme;
import com.nextgame.entities.Utilisateur;
import com.nextgame.mappers.JeuMapperImpl;
import com.nextgame.services.JeuService;
import com.nextgame.services.MyUserDetailsService;
import com.nextgame.utils.JwtService;

@SpringBootTest
@AutoConfigureMockMvc
public class JeuControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	 
	private String uri = "/jeux";
	 
	@Autowired
	private JwtService jwtService;
	 
	private String jwtToken;
	 
	@MockBean
	private MyUserDetailsService myUserDetailsService;
	 
	@MockBean
	private JeuService jeuService;
	 
	@MockBean
	private JeuMapperImpl jeuMapperImpl;
	
	@BeforeEach
	public void generationJWT() {
		Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setEmail("test@gmail.com");
	    Mockito.when(myUserDetailsService.loadUserByUsername("test@gmail.com"))
	           .thenReturn(new User("test@gmail.com", "password", new ArrayList<>()));
	    jwtToken = jwtService.genererToken(utilisateur);
	 }
	
	@Test
	public void getAllJeuxTest() throws Exception {
		Plateforme plateforme = new Plateforme(1L,"PS5", "");
		Plateforme plateforme2 = new Plateforme(2L,"PC", "");
		
		//PlateformeDTO plateformeDTO = new PlateformeDTO(1L,"PS5", "");
		//PlateformeDTO plateforme2DTO = new PlateformeDTO(2L,"PC", "");
		
		List<Plateforme> listePlateformes = List.of(plateforme, plateforme2);
		//List<PlateformeDTO> listePlateformesDTO = List.of(plateformeDTO, plateforme2DTO);
		
		Jeu jeu1 = new Jeu();
		jeu1.setId(1L);
		jeu1.setNom("Cyberpunk 2077");
		jeu1.setPlateformes(listePlateformes);
		
		Jeu jeu2 = new Jeu();
		jeu2.setId(2L);
		jeu2.setNom("Red dead Redemption 2");
		jeu2.setPlateformes(listePlateformes);
		
		Jeu jeu3 = new Jeu();
		jeu3.setId(3L);
		jeu3.setNom("The last of us part 1");
		jeu3.setPlateformes(listePlateformes);
		
		JeuDTO jeuDTO1 = new JeuDTO();
		jeuDTO1.setId(1L);
		jeuDTO1.setNom("Cyberpunk 2077");
		jeuDTO1.setPlateformes(listePlateformes);
		
		JeuDTO jeuDTO2 = new JeuDTO();
		jeuDTO2.setId(2L);
		jeuDTO2.setNom("Red dead Redemption 2");
		jeuDTO2.setPlateformes(listePlateformes);
		
		JeuDTO jeuDTO3 = new JeuDTO();
		jeuDTO3.setId(3L);
		jeuDTO3.setNom("The last of us part 1");
		jeuDTO3.setPlateformes(listePlateformes);
		 
		List<Jeu> listeJeux = List.of(jeu1, jeu2, jeu3);
		 
		//Mock des appels des services et du mapper to DTO
		when(jeuService.getAll()).thenReturn(listeJeux);
		when(jeuMapperImpl.mapToDto(jeu1)).thenReturn(jeuDTO1);
		when(jeuMapperImpl.mapToDto(jeu2)).thenReturn(jeuDTO2);
		when(jeuMapperImpl.mapToDto(jeu3)).thenReturn(jeuDTO3);
		 
		//Mock pour la requete GET
		mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		 	.andExpect(MockMvcResultMatchers.status().isOk())
		 	.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L)) 
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Cyberpunk 2077"))
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[0].plateformes[0].nom").value("PS5"))
	        .andExpect(MockMvcResultMatchers.jsonPath("$[0].plateformes[1].nom").value("PC"))
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L)) 
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("Red dead Redemption 2"))
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[1].plateformes[0].nom").value("PS5"))
	        .andExpect(MockMvcResultMatchers.jsonPath("$[1].plateformes[1].nom").value("PC"))
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3L))
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[2].nom").value("The last of us part 1"))
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[2].plateformes[0].nom").value("PS5"))
	        .andExpect(MockMvcResultMatchers.jsonPath("$[2].plateformes[1].nom").value("PC"));
	}
	
	@Test
	public void getJeuByIdTest() throws Exception {
		Plateforme plateforme = new Plateforme(1L,"PS5", "");
		Plateforme plateforme2 = new Plateforme(2L,"PC", "");
		
		List<Plateforme> listePlateformes = List.of(plateforme, plateforme2);
		//List<String> listePlateformesDTO = List.of("PS5", "PC");
		
		Jeu jeu = new Jeu();
		jeu.setId(1L);
		jeu.setNom("Cyberpunk 2077");
		jeu.setPlateformes(listePlateformes);
		
		JeuDTO jeuDTO = new JeuDTO();
		jeuDTO.setId(1L);
		jeuDTO.setNom("Cyberpunk 2077");
		jeuDTO.setPlateformes(listePlateformes);
		
		//Mock des appels des services et du mapper to DTO
		when(jeuService.existById(1L)).thenReturn(true);
		when(jeuService.getById(1L)).thenReturn(jeu);
		when(jeuMapperImpl.mapToDto(jeu)).thenReturn(jeuDTO);
		 
		//Mock pour la requete GET
		mockMvc.perform(MockMvcRequestBuilders.get(uri + "/{id}", 1L)
				.accept(MediaType.APPLICATION_JSON))
 	 			.andExpect(status().isOk())
 	 			.andExpect(jsonPath("$.id").value(1L))
 	 			.andExpect(jsonPath("$.nom").value("Cyberpunk 2077"))
 			 	.andExpect(MockMvcResultMatchers.jsonPath("$.plateformes[0].nom").value("PS5"))
 		        .andExpect(MockMvcResultMatchers.jsonPath("$.plateformes[1].nom").value("PC"));
	}
	
	@Test
	public void postTest() throws Exception {
		Plateforme plateforme = new Plateforme(1L,"PS5", "");
		Plateforme plateforme2 = new Plateforme(2L,"PC", "");
		
		List<Plateforme> listePlateformes = List.of(plateforme, plateforme2);
		
		List<String> listePlateformesDTO = List.of("PS5", "PC");
		
		Jeu jeu = new Jeu();
		jeu.setNom("The witcher 4");
		jeu.setPlateformes(listePlateformes);
		
		JeuDTO jeuDTO = new JeuDTO();
		jeuDTO.setNom("The witcher 4");
		jeuDTO.setPlateformes(listePlateformes);
		
		//Mock des appels des services et du mapper to DTO
		when(jeuMapperImpl.mapToDto(jeu)).thenReturn(jeuDTO);
		when(jeuService.getById(1L)).thenReturn(jeu);
		
		//Mock pour la requete POST
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(jeu)))
		 .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void updateTest() throws JsonProcessingException, Exception {
		Plateforme plateforme = new Plateforme(1L,"PS5", "");
		Plateforme plateforme2 = new Plateforme(2L,"PC", "");
		
		List<Plateforme> listePlateformes = List.of(plateforme, plateforme2);
		
		Jeu jeu = new Jeu();
		jeu.setId(1l);
		jeu.setNom("The wicher 4");
		jeu.setPlateformes(listePlateformes);
		
		Jeu jeuUpdated = new Jeu();
		jeuUpdated.setId(1l);
		jeuUpdated.setNom("The witcher 4");
		
		JeuDTO jeuUpdatedDTO = new JeuDTO();
		jeuUpdatedDTO.setId(1L);
		jeuUpdatedDTO.setNom("The witcher 4");
		
		// Mock des appels de service et mapper
	    when(jeuService.existById(1L)).thenReturn(true);
	    when(jeuService.getById(1L)).thenReturn(jeu);
	    when(jeuService.update(any(Jeu.class))).thenReturn(jeuUpdated);
	    when(jeuMapperImpl.mapToDto(jeuUpdated)).thenReturn(jeuUpdatedDTO);
		
		//Mock pour la requete PUT
		mockMvc.perform(MockMvcRequestBuilders.put(uri + "/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(jeuUpdatedDTO))
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$.id").value(1L))
			.andExpect(jsonPath("$.nom").value("The witcher 4"));
	}
	
	@Test
	public void deleteTest() throws Exception {
		// On simule l'existance de la ressource pour ne pas avoir une erreur 404
		when(jeuService.existById(1L)).thenReturn(true);
	    doNothing().when(jeuService).delete(1L);
	    
	    //Mock pour la requete DELETE
		mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + jwtToken)
				.accept(MediaType.APPLICATION_JSON))
	    	.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	public void getJeuxByDateTest() throws Exception {
		Plateforme plateforme = new Plateforme(1L,"PS5", "");
		Plateforme plateforme2 = new Plateforme(2L,"PC", "");
		
		List<Plateforme> listePlateformes = List.of(plateforme, plateforme2);
		
		List<String> listePlateformesDTO = List.of("PS5", "PC");
		
		Jeu jeu = new Jeu();
		jeu.setId(1L);
		jeu.setNom("Kingdom Come deliverance 2");
		jeu.setPlateformes(listePlateformes);
		LocalDate dateSortie = LocalDate.parse("2025-02-01");
		jeu.setDateSortie(dateSortie);
		
		Jeu jeu2 = new Jeu();
		jeu2.setId(2L);
		jeu2.setNom("Avowed");
		jeu2.setPlateformes(listePlateformes);
		LocalDate dateSortie2 = LocalDate.parse("2025-02-18");
		jeu2.setDateSortie(dateSortie2);
		
		Jeu jeu3 = new Jeu();
		jeu3.setId(3L);
		jeu3.setNom("Monster Hunter wilds");
		jeu3.setPlateformes(listePlateformes);
		LocalDate dateSortie3 = LocalDate.parse("2025-02-28");
		jeu3.setDateSortie(dateSortie3);
		
		List<Jeu> listeJeux = List.of(jeu, jeu2, jeu3);
		
		//DTO's
		JeuDTO jeuDTO = new JeuDTO();
		jeuDTO.setId(1L);
		jeuDTO.setNom("Kingdom Come deliverance 2");
		jeuDTO.setPlateformes(listePlateformes);
		LocalDate dateSortieDTO = LocalDate.parse("2025-02-01");
		jeuDTO.setDateSortie(dateSortieDTO);
		
		JeuDTO jeuDTO2 = new JeuDTO();
		jeuDTO2.setId(2L);
		jeuDTO2.setNom("Avowed");
		jeuDTO2.setPlateformes(listePlateformes);
		LocalDate dateSortieDTO2 = LocalDate.parse("2025-02-18");
		jeuDTO2.setDateSortie(dateSortieDTO2);
		
		JeuDTO jeuDTO3 = new JeuDTO();
		jeuDTO3.setId(3L);
		jeuDTO3.setNom("Monster Hunter wilds");
		jeuDTO3.setPlateformes(listePlateformes);
		LocalDate dateSortieDTO3 = LocalDate.parse("2025-02-28");
		jeuDTO3.setDateSortie(dateSortieDTO3);
		
		 // Mock des appels des services et du mapper to DTO
	    when(jeuService.getAllJeuxByAnneeSortie(2025)).thenReturn(listeJeux);
	    when(jeuMapperImpl.mapToDto(jeu)).thenReturn(jeuDTO);
	    when(jeuMapperImpl.mapToDto(jeu2)).thenReturn(jeuDTO2);
	    when(jeuMapperImpl.mapToDto(jeu3)).thenReturn(jeuDTO3);
		 
		//Mock pour la requete GET
	    mockMvc.perform(MockMvcRequestBuilders.get(uri + "/date/{annee}", 2025)
				 .accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.size()").value(3))
	        .andExpect(jsonPath("$[0].id").value(1L))
	        .andExpect(jsonPath("$[0].nom").value("Kingdom Come deliverance 2"))
	        .andExpect(jsonPath("$[0].dateSortie").value("2025-02-01"));
	}
	
	@Test
	public void getJeuxByGenreIdTest() throws Exception {
 		
		Genre rpg = new Genre(1L,"RPG");
		Genre action = new Genre(2L,"Action");
		Genre openW = new Genre(3L,"OpenW");
		
		List<Genre> actionRPG = List.of(rpg, action);
		List<Genre> actionRPGOW = List.of(rpg, action, openW);
		
		Jeu jeu = new Jeu();
		jeu.setId(1L);
		jeu.setNom("Kingdom Come deliverance 2");
		jeu.setGenres(actionRPGOW);
		
		Jeu jeu2 = new Jeu();
		jeu2.setId(2L);
		jeu2.setNom("Avowed");
		jeu2.setGenres(actionRPGOW);
		
		Jeu jeu3 = new Jeu();
		jeu3.setId(3L);
		jeu3.setNom("Monster Hunter wilds");
		jeu3.setGenres(actionRPG);
		
		List<Jeu> listeJeux = List.of(jeu, jeu2, jeu3);
		
		//DTO's
		JeuDTO jeuDTO = new JeuDTO();
		jeuDTO.setId(1L);
		jeuDTO.setNom("Kingdom Come deliverance 2");
		jeuDTO.setGenres(List.of(rpg, action, openW));
		
		JeuDTO jeuDTO2 = new JeuDTO();
		jeuDTO2.setId(2L);
		jeuDTO2.setNom("Avowed");
		jeuDTO2.setGenres(List.of(rpg, action, openW));
		
		JeuDTO jeuDTO3 = new JeuDTO();
		jeuDTO3.setId(3L);
		jeuDTO3.setNom("Monster Hunter wilds");
		jeuDTO3.setGenres(List.of(rpg, action, openW));
		
		 // Mock des appels des services et du mapper to DTO
	    when(jeuService.getAllJeuxByGenreId(1L)).thenReturn(listeJeux);
	    when(jeuMapperImpl.mapToDto(jeu)).thenReturn(jeuDTO);
	    when(jeuMapperImpl.mapToDto(jeu2)).thenReturn(jeuDTO2);
	    when(jeuMapperImpl.mapToDto(jeu3)).thenReturn(jeuDTO3);
		 
		//Mock pour la requete GET
	    mockMvc.perform(MockMvcRequestBuilders.get(uri + "/genre/{id}", 1L)
				 .accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.size()").value(3))
	        .andExpect(jsonPath("$[0].id").value(1L))
	        .andExpect(jsonPath("$[0].nom").value("Kingdom Come deliverance 2"))
	        .andExpect(jsonPath("$[0].genres[0].libelle").value("RPG"))
	        .andExpect(jsonPath("$[0].genres[1].libelle").value("Action"))
	        .andExpect(jsonPath("$[0].genres[2].libelle").value("OpenW"))
		    .andExpect(jsonPath("$[1].id").value(2L))
	        .andExpect(jsonPath("$[1].nom").value("Avowed"))
	        .andExpect(jsonPath("$[1].genres[0].libelle").value("RPG"))
	        .andExpect(jsonPath("$[1].genres[1].libelle").value("Action"))
	        .andExpect(jsonPath("$[1].genres[2].libelle").value("OpenW"))
		    .andExpect(jsonPath("$[2].id").value(3L))
	        .andExpect(jsonPath("$[2].nom").value("Monster Hunter wilds"))
	        .andExpect(jsonPath("$[2].genres[0].libelle").value("RPG"))
	        .andExpect(jsonPath("$[2].genres[1].libelle").value("Action"))
	        .andExpect(jsonPath("$[2].genres[2].libelle").value("OpenW"));
	}
	
	//TODO: finir ces tests
	public void getJeuxBySameFranchiseTest() {
		
	}
	
	public void getJeuxByDeveloppeurIdTest() {
		
	}
	
	public void getListeDetailsJeuxTest() {
		
	}
}
