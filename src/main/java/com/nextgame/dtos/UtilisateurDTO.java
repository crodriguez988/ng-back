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
	
	private String mail;
	private String pseudo;
	
	@Override
	public String toString() {
		return "UtilisateurDTO [mail=" + mail + ", pseudo=" + pseudo + "]";
	}
}