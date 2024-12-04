package com.nextgame.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextgame.dtos.JeuUtilisateurDTO;
import com.nextgame.entities.JeuUtilisateur;

public class JeuUtilisateurMapperImpl implements Mapper<JeuUtilisateur, JeuUtilisateurDTO>{

	@Autowired
	private ModelMapper modelMapper;
	
	public JeuUtilisateurMapperImpl() {
		super();
	}
	
	@Override
	public JeuUtilisateurDTO mapToDto(JeuUtilisateur utilisateurJeu) {
		return modelMapper.map(utilisateurJeu, JeuUtilisateurDTO.class);
	}

	@Override
	public JeuUtilisateur mapToEntity(JeuUtilisateurDTO utilisateurJeuDTO) {
		return modelMapper.map(utilisateurJeuDTO, JeuUtilisateur.class);
	}
}