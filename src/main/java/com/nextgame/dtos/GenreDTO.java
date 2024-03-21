package com.nextgame.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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