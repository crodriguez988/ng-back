package com.nextgame.enums;

/**
 * Enumération pour l'état de complétion du jeu 
 */
public enum EtatJeu {
	PAS_COMMENCE("Pas commencé"), EN_COURS("En cours"), TERMINE("Terminé"), ABANDONNE("Abandonné");

	private String etat;
	
	EtatJeu(String etat) {
		this.etat = etat;
	}
	
	public String getEtat() {
		return etat;
	}
}