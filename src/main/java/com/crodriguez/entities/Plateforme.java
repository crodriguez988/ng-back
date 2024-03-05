package com.crodriguez.entities;

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
public class Plateforme implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** L'id de la plateforme */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plateforme_id")
	private long id;
	
	/** Nom de la plateforme (PC / PS5 / XBOX etc) */
	@Column(name = "nom")
	private String nom;
	
	@Override
	public String toString() {
		return "Plateforme [id=" + id + ", nom=" + nom + "]";
	}
} 