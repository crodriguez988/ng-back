package com.crodriguez.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Jeu implements Serializable {
	
	/**	 */
	private static final long serialVersionUID = -8703680295045491791L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	private Date dateSortie;
	private String synopsis;
	private Boolean goty;
	private Boolean solo;
	private Boolean cooperatif;
	private Boolean multijoueur;
	
	public Jeu(long id, String nom, Date dateSortie, String synopsis, Boolean goty, Boolean solo, Boolean cooperatif,
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