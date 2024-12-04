package com.nextgame.dtos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class JeuUtilisateurDTO {
	
	/** Id jeuUtilisateur **/
	private Long id;
	
	/** Id de l'utilisateur **/
	private Long idUtilisateur;
	
	/** Id du jeu **/
	private Long idJeu;
	
	/** Note du jeu **/
	private double note;
	
	/** Date d'ajout du jeu (a la liste de jeux de l'utilisateur) **/
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateAjout;
    
	/** Etat du jeu (TERMINE, ENCOURS, etc) **/
	private EtatJeu statutJeu;
    
	/** Nom du jeu**/
	private String nomJeu;
    
	/** La date ou le jeu a été terminé **/
	@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTermine;
    
	/** Plateforme dans lesquelles il a été fini **/
	private List<String> plateformes;
    
	@Override
	public String toString() {
		return "UtilisateurJeuDTO [id=" + id + ", idUtilisateur=" + idUtilisateur + ", idJeu=" + idJeu + ", note="
				+ note + ", dateAjout=" + dateAjout + ", statutJeu=" + statutJeu + ", nomJeu=" + nomJeu
				+ ", dateTermine=" + dateTermine + ", plateformes=" + plateformes + "]";
	}
}