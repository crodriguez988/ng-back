package com.nextgame.dtos;

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
public class UtilisateurDTO {
	
	/** Id de l'utilisateur */
	private Long id;
	
	/** Email de l'utilisateur */
	private String email;
	
	/** Nom de l'utilisateur */
	private String nomUtilisateur;
	
	/** Mot de passe de l'utilisateur */
	private String motDePasse;
	
	@Override
	public String toString() {
		return "UtilisateurDTO [id=" + id + ", email=" + email + ", nomUtilisateur=" + nomUtilisateur + ", motDePasse="
				+ motDePasse + "]";
	}
}