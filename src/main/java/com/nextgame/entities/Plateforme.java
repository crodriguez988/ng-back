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
@Table(name = "PLATEFORME")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * Cette classe contient les informations liées à une plateforme (PC, PS5, Xbox, etc...)
 */
public class Plateforme implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** L'id de la plateforme */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plateforme")
	private long id;
	
	/** Nom de la plateforme (PC / Play Station 5 / XBOX etc) */
	@Column(name = "nom")
	private String nom;
	
	/** Abreviation du nom de la console */
	@Column(name = "abreviation")
	private String abreviation;

	@Override
	public String toString() {
		return "Plateforme [id=" + id + ", nom=" + nom + ", abreviation=" + abreviation + "]";
	}
} 