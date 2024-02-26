package com.crodriguez.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
	
	private String mail;
	private String pseudo;
	
	@Override
	public String toString() {
		return "UtilisateurDTO [mail=" + mail + ", pseudo=" + pseudo + "]";
	}
}