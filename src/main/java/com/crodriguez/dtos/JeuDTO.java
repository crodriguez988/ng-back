package com.crodriguez.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JeuDTO {
	private Long id;
	private String nom;
	private Date dateSortie;
	private String synopsis;
	private Boolean goty;
	private Boolean solo;
	private Boolean cooperatif;
	private Boolean multijoueur;
}