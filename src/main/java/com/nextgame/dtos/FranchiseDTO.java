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
public class FranchiseDTO {

	/** L'id de l'a franchise */
	private long id;
	
	/** Le nom la franchise */
	private String nom;
	
	/** Abréviation de la franchise */
	private String abreviation;

	@Override
	public String toString() {
		return "FranchiseDTO [id=" + id + ", nom=" + nom + ", abreviation=" + abreviation + "]";
	}
}