package com.nextgame.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FRANCHISE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * Cette classe contient les informations d'une franchise de jeux-vidéo
 * Une franchise est un ensemble de jeux qui partagent un univers commun, des personnages récurrents, une histoire ou un thème similaire
 */
public class Franchise {
	/** Id de la franchise de jeux-vidéos*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_franchise")
	private long id;
	
	/** Nom de la franchise */
	private String nom;

	@Override
	public String toString() {
		return "Franchise [id=" + id + ", nom=" + nom + "]";
	}	
}