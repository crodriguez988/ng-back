package com.nextgame.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

/**
 * Cette entité contient toutes les informations relatives à un jeu-vidéo
 */
public class Jeu implements Serializable {
	
	private static final long serialVersionUID = -8703680295045491791L;

	/** L'id du jeu-video */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	/** Le nom du jeu */
	@Column(name = "nom")
	private String nom;
	
	/** La date sortie du jeu */
	@Column(name = "date_sortie")
	private LocalDate dateSortie;
	
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
	
	/** Cette liste créée une table d'association entre Jeu et Genre */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "jeu_genre",
				joinColumns = @JoinColumn (name = "id_jeu"),
				inverseJoinColumns = @JoinColumn ( name = "id_genre" ))
	private List<Genre> genres = new ArrayList<>();
	
	/** Cette liste créée une table d'association entre Jeu et Developpeur */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "jeu_developpeur",
				joinColumns = @JoinColumn (name = "id_jeu"),
				inverseJoinColumns = @JoinColumn ( name = "id_developpeur" ))
	private List<Developpeur> developpeurs = new ArrayList<>();
	
	/** Cette liste créée une table d'association entre Jeu et Editeur */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "jeu_editeur",
				joinColumns = @JoinColumn (name = "id_jeu"),
				inverseJoinColumns = @JoinColumn ( name = "id_editeur" ))
	private List<Editeur> editeurs = new ArrayList<>();
	
	/** Cette liste créée une table d'association entre Jeu et Plateforme */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "jeu_plateforme",
				joinColumns = @JoinColumn (name = "id_jeu"),
				inverseJoinColumns = @JoinColumn ( name = "id_plateforme" ))
	private List<Plateforme> plateformes = new ArrayList<>();
	
	/** Un jeu peut appartenir à une franchise de jeux */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_franchise", nullable = true)
	@JsonBackReference
	private Franchise franchise;
	
	/** Lien vers l'utilisateur, crée une table d'association */
	@OneToMany(mappedBy = "jeu", cascade = CascadeType.ALL)
	private List<JeuUtilisateur> utilisateursJeux = new ArrayList<>();

	public Jeu(long id, String nom, LocalDate dateSortie, String synopsis, Boolean goty, Boolean solo, Boolean cooperatif,
			Boolean multijoueur, List<Genre> genres, List<Developpeur> developpeurs, List<Editeur> editeurs,
			List<Plateforme> plateformes, Franchise franchise) {
		super();
		this.id = id;
		this.nom = nom;
		this.dateSortie = dateSortie;
		this.synopsis = synopsis;
		this.goty = goty;
		this.solo = solo;
		this.cooperatif = cooperatif;
		this.multijoueur = multijoueur;
		this.genres = genres;
		this.developpeurs = developpeurs;
		this.editeurs = editeurs;
		this.plateformes = plateformes;
		this.franchise = franchise;
	}

	@Override
	public String toString() {
		return "Jeu [id=" + id + ", nom=" + nom + ", dateSortie=" + dateSortie + ", synopsis=" + synopsis + ", goty="
				+ goty + ", solo=" + solo + ", cooperatif=" + cooperatif + ", multijoueur=" + multijoueur + ", genres="
				+ genres + ", developpeurs=" + developpeurs + ", editeurs=" + editeurs + ", plateformes=" + plateformes
				+ ", franchise=" + franchise + "]";
	}
	
}