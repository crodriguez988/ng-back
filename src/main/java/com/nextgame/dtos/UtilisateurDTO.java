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
	
	private String email;
	private String pseudonyme;
	private String motDePasse;
	@Override
	public String toString() {
		return "UtilisateurDTO [email=" + email + ", pseudonyme=" + pseudonyme + ", motDePasse=" + motDePasse + "]";
	}
}