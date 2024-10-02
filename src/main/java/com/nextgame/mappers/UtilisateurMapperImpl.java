package com.nextgame.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextgame.dtos.UtilisateurDTO;
import com.nextgame.entities.Utilisateur;

public class UtilisateurMapperImpl implements Mapper<Utilisateur, UtilisateurDTO>{

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UtilisateurDTO mapToDto(Utilisateur utilisateur) {
		return modelMapper.map(utilisateur, UtilisateurDTO.class);
	}

	@Override
	public Utilisateur mapToEntity(UtilisateurDTO utilisateurDTO) {
		return modelMapper.map(utilisateurDTO, Utilisateur.class);
	}
}