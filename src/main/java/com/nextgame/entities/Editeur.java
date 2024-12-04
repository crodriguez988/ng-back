package com.nextgame.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
 * Cette entité contient les informations d'un éditeur pour un jeu vidéo
 * L'éditeur s'occupe de la distribution, publicité, etc pour un jeu-vidéo
 */
public class Editeur implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** L'id de l'éditeur*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_editeur")
	private long id;
	
	/** Le nom de l'éditeur*/
	@Column(name = "nom", length = 300)
	private String nom;
	
	@Override
	public String toString() {
		return "Editeur [id=" + id + ", nom=" + nom + "]";
	}
}