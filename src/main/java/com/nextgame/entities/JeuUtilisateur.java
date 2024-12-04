package com.nextgame.entities;

import java.time.LocalDate;
import java.util.List;

import com.nextgame.enums.EtatJeu;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * Cette entité contient les données de la liste de jeux pour un utilisateur 
 */
public class JeuUtilisateur {
	
	/** Id jeuUtilisateur */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	/** Id de l'utilisateur*/
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", nullable = false)
	private Utilisateur utilisateur;
	
	/** Id du jeu */
	@ManyToOne
	@JoinColumn(name = "id_jeu", nullable = false)
	private Jeu jeu;
	
	/** Note du jeu (allant de 1 à 10 ) */
	private double note;
	
	/** Date d'ajout du jeu */
	@Column(name = "date_ajout")
	private LocalDate dateAjout;
	
	/** Etat du jeu */
 	@Enumerated(EnumType.STRING)
    private EtatJeu statutJeu;
 	
 	/**
 	 * Date à laquelle l'utilisateur a terminé le jeu
 	 */
 	@Nullable
 	@Column(name = "date_termine")
 	private LocalDate dateTermine;
 	
 	/** Plateforme dans laquelle le jeu a été joué */
 	private List<Plateforme> plateformes;
}