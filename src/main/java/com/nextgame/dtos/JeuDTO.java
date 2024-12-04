package com.nextgame.dtos;

import java.time.LocalDate;
import java.util.List;

import com.nextgame.entities.Developpeur;
import com.nextgame.entities.Editeur;
import com.nextgame.entities.Genre;
import com.nextgame.entities.Plateforme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JeuDTO {
	
	/** Id du jeu **/
	private long  id;
	
	/** Nom du jeu **/
	private String nom;
	
	/** Date de sortie du jeu **/
	private LocalDate dateSortie;
	
	/** Synopsis du jeu **/
	private String synopsis;
	
	/** GOTY **/
	private Boolean goty;
	
	/** Jeu solo **/
	private Boolean solo;
	
	/** Jeu Coopératif **/
	private Boolean cooperatif;
	
	/** Jeu multi-joueur **/
	private Boolean multijoueur;
	
	/** Genres du jeu **/
	private List<Genre> genres;
	
	/** Développeur(s) du jeu **/
	private List<Developpeur> developpeurs;
	
	/** Editeur(s) du jeu **/
	private List<Editeur> editeurs;
	
	/** Plateforme(s) du jeu **/
	private List<Plateforme> plateformes;
	
	/** Franchise à laquelle peut appartenir le jeu **/
	private FranchiseDTO franchise; 
	
	@Override
	public String toString() {
		return "JeuDTO [id=" + id + ", nom=" + nom + ", dateSortie=" + dateSortie + ", synopsis=" + synopsis + ", goty="
				+ goty + ", solo=" + solo + ", cooperatif=" + cooperatif + ", multijoueur=" + multijoueur + ", genres="
				+ genres + ", developpeurs=" + developpeurs + ", editeurs=" + editeurs + ", plateformes=" + plateformes
				+ ", franchise=" + franchise + "]";
	}
}