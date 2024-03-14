package com.crodriguez.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {
	
	/** L'id du genre */
	private long id;
	
	/** Le libellé du genre */
	private String libelle;
	
	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + libelle + "]";
	}
}