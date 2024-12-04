package com.nextgame.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
 * Cette entité contient les informations liées à un utilisateur.
 */
public class Utilisateur implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** Id de l'utilisateur */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	/** Email de l'utilisateur */
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	/** Nom de l'utilisateur */
	@Column(name = "nomUtilisateur", unique = true)
	private String nomUtilisateur;
	
	/** Mot de passe de l'utilisateur */
	@Column(name = "motDePasse", nullable = false)
	private String motDePasse;
	
	
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	private List<JeuUtilisateur> jeuxByUtilisateur = new ArrayList<>();
}