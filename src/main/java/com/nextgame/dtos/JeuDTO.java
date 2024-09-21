package com.nextgame.dtos;

import java.util.Date;
import java.util.List;

import com.nextgame.entities.Developpeur;
import com.nextgame.entities.Editeur;
import com.nextgame.entities.Franchise;
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
	
	private long  id;
	private String nom;
	private Date dateSortie;
	private String synopsis;
	private Boolean goty;
	private Boolean solo;
	private Boolean cooperatif;
	private Boolean multijoueur;
	private List<Genre> genres;
	private List<Developpeur> developpeurs;
	private List<Editeur> editeurs;
	private List<Plateforme> plateformes;
	private Franchise franchise;
	@Override
	public String toString() {
		return "JeuDTO [id=" + id + ", nom=" + nom + ", dateSortie=" + dateSortie + ", synopsis=" + synopsis + ", goty="
				+ goty + ", solo=" + solo + ", cooperatif=" + cooperatif + ", multijoueur=" + multijoueur + ", genres="
				+ genres + ", developpeurs=" + developpeurs + ", editeurs=" + editeurs + ", plateformes=" + plateformes
				+ ", franchise=" + franchise + "]";
	}
}