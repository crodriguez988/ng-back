package com.crodriguez.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditeurDTO {
	
	private long id;
	private String nom;
	
	@Override
	public String toString() {
		return "Editeur [id=" + id + ", nom=" + nom + "]";
	}
}