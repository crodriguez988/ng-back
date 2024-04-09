package com.nextgame.entities;

import java.io.Serializable;

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
@Table(name = "STUDIO_DEV")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * Cette classe permet de gérer le(s) Studio(s) qui a/ont développé le jeu
 */
public class StudioDev implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** L'id du Studio */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_studio")
	private long id;
	
	/** Nom du Studio */
	@Column(name = "nom")
	private String nom;

	@Override
	public String toString() {
		return "StudioDev [id=" + id + ", nom=" + nom + "]";
	}
}