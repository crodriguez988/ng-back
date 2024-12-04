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
 * Cette entité contient les différents genres pour un jeu-vidéo (action, FPS, TPS, etc...)
 */
public class Genre implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_genre")
	private long id;

	/** Indique le nom du genre */
	@Column(name = "libelle")
	private String libelle;
	
	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + libelle + "]";
	}
}