package com.crodriguez.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "JEU")
@Getter
@Setter
@NoArgsConstructor
/**
 * Cette classe contient toutes les informations relatives à un jeu-vidéo
 */
public class Jeu implements Serializable {
	
	private static final long serialVersionUID = -8703680295045491791L;

	/** L'id du jeu-video */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jeu_id")
	private long id;
	
	/** Le nom du jeu */
	@Column(name = "nom")
	private String nom;
	
	/** La date sortie du jeu */
	@Column(name = "date_sortie")
	private Date dateSortie;
	
	/** Le synopsis du jeu*/
	@Column(name = "synopsis")
	private String synopsis;
	
	/** Indique si le jeu est "GOTY" Game for The Year */
	@Column(name = "goty")
	private Boolean goty;
	
	/** Indique si le jeu est jouable à une personne */
	@Column(name = "solo")
	private Boolean solo;
	
	/** Indique si le jeu est jouable en coopération */
	@Column(name = "cooperatif")
	private Boolean cooperatif;
	
	/** Indique si le jeu est jouable en multijoueur */
	@Column(name = "multijoueur")
	private Boolean multijoueur;
	
	/** Cette liste permet de créer une table d'association entre Jeu et Genre */
	@ManyToMany
	@JoinTable( name = "genre_jeu",
				joinColumns = @JoinColumn (name = "jeu_id"),
				inverseJoinColumns = @JoinColumn ( name = "genre_id" ))
	private List<Genre> genres = new ArrayList<>();
	
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