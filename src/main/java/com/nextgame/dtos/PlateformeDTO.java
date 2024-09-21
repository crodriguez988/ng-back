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
public class PlateformeDTO {
	
	/** L'id de la plateforme */
	private long id;
	
	/** Le nom de la plateforme */
	private String nom;
	
	/** Abréviation du nom de la plateforme */
	private String abreviation;

	@Override
	public String toString() {
		return "PlateformeDTO [id=" + id + ", nom=" + nom + ", abreviation=" + abreviation + "]";
	}
}