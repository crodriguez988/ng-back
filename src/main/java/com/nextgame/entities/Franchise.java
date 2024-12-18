package com.nextgame.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
 * Cette entité contient les informations d'une franchise de jeux-vidéo
 * Une franchise est un ensemble de jeux qui partagent un univers commun, des personnages récurrents, une histoire ou un thème similaire
 */
public class Franchise implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** Id de la franchise de jeux-vidéos*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_franchise")
	private long id;
	
	/** Nom de la franchise */
	private String nom;
	
	/** Abrviation de la franchise */
	private String abreviation;

	@OneToMany(mappedBy = "franchise")
	@JsonManagedReference
	private List<Jeu> jeux;
	
	@Override
	public String toString() {
		return "Franchise [id=" + id + ", nom=" + nom + "]";
	}	
}