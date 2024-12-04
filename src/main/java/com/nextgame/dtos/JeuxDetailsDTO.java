package com.nextgame.dtos;

import java.time.LocalDate;

import com.nextgame.enums.EtatJeu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JeuxDetailsDTO {
	/** Nom du jeu **/
	private String nom;
	
	/** Développeur(s) du jeu **/
	private String developpeur;
	
	/** Date de sortie du jeu **/
	private LocalDate dateSortie;
	
	/** Etat actuel du jeu (si user connecté) **/
	private EtatJeu etatJeu;
	
	/** Note attribué (si user connecté) **/
	private double note;
	
	@Override
	public String toString() {
		return "DetailsJeuDTO [nom=" + nom + ", developpeur=" + developpeur + ", dateSortie=" + dateSortie
				+ ", etatJeu=" + etatJeu + ", note=" + note + "]";
	}
}