package com.nextgame.dtos;

import java.util.ArrayList;
import java.util.List;

import com.nextgame.entities.Jeu;

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

	/** Liste des jeux appartenant à une franchise */
	private List<Jeu> jeux = new ArrayList<>();

	@Override
	public String toString() {
		return "FranchiseDTO [id=" + id + ", nom=" + nom + ", jeux=" + jeux + "]";
	}
}