package com.nextgame.dtos;

import java.util.Date;
import java.util.List;

import com.nextgame.entities.Editeur;
import com.nextgame.entities.Genre;
import com.nextgame.entities.Plateforme;
import com.nextgame.entities.StudioDev;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JeuDTO {
	
	private long  id;
	private String nom;
	private Date dateSortie;
	private String synopsis;
	private Boolean goty;
	private Boolean solo;
	private Boolean cooperatif;
	private Boolean multijoueur;
	private List<Genre> genres;
	private List<StudioDev> studiosDev;
	private List<Editeur> editeurs;
	private List<Plateforme> plateformes;
}