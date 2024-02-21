package com.crodriguez.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Jeu {
	
	// TODO : Finir de mettre les @id etc etcs
	private Long id;
	private String nom;
	private Date dateSortie;
	private String synopsis;
	private Boolean goty;
	private Boolean solo;
	private Boolean cooperatif;
	private Boolean multijoueur;
	
	public Jeu(Long id, String nom, Date dateSortie, String synopsis, Boolean goty, Boolean solo, Boolean cooperatif,
			Boolean multijoueur) {
		super();
		this.id = id;
		this.nom = nom;
		this.dateSortie = dateSortie;
		this.synopsis = synopsis;
		this.goty = goty;
		this.solo = solo;
		this.cooperatif = cooperatif;
		this.multijoueur = multijoueur;
	}

	@Override
	public String toString() {
		return "Jeu [id=" + id + ", nom=" + nom + ", dateSortie=" + dateSortie + ", synopsis=" + synopsis + ", goty="
				+ goty + ", solo=" + solo + ", cooperatif=" + cooperatif + ", multijoueur=" + multijoueur + "]";
	}
}