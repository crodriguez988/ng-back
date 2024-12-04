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
 * Cette entité contient les informations d'un développeur (Studio(s)) qui a/ont développé le jeu
 */
public class Developpeur implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** L'id du developpeur */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_developpeur")
	private long id;
	
	/** Nom du developpeur */
	@Column(name = "nom")
	private String nom;

	@Override
	public String toString() {
		return "Developpeur [id=" + id + ", nom=" + nom + "]";
	}
}