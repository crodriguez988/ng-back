package com.crodriguez.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlateformeDTO {
	
	/** L'id de la plateforme */
	private long id;
	
	/** Le nom de la plateforme */
	private String nom;
	
	@Override
	public String toString() {
		return "PlateformeDTO [id=" + id + ", nom=" + nom + "]";
	}
}