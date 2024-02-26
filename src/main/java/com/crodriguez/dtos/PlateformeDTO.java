package com.crodriguez.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlateformeDTO {
	
	private Long id;
	private String nom;
	
	@Override
	public String toString() {
		return "PlateformeDTO [id=" + id + ", nom=" + nom + "]";
	}
}