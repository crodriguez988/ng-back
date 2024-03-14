package com.crodriguez.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudioDevDTO {
	
	/** L'id du studio développeur */
	private long id;
	
	/** Le nom du studio */
	private String nom;

	@Override
	public String toString() {
		return "StudioDev [id=" + id + ", nom=" + nom + "]";
	}
}