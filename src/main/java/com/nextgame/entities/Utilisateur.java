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
@Table(name = "UTILISATEUR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * Cette classe contient les informations liées à un utilisateur.
 */
public class Utilisateur {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "pseudonyme", unique = true)
	private String pseudonyme;
	
	@Column(name = "motDePasse", nullable = false)
	private String motDePasse;
}