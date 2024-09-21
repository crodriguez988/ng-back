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
public class DeveloppeurDTO {
	
	/** L'id du studio développeur */
	private long id;
	
	/** Le nom du développeur */
	private String nom;

	@Override
	public String toString() {
		return "Developpeur [id=" + id + ", nom=" + nom + "]";
	}
}