package com.nextgame.enums;

public enum EtatJeu {
	AFAIRE("A faire"), ENCOURS("En cours"), TERMINE("Terminé");

	private String etat;
	
	EtatJeu(String etat) {
		this.etat = etat;
	}
	
	public String getEtat() {
		return etat;
	}
}