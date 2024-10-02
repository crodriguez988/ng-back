package com.nextgame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextgame.entities.Utilisateur;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	
	public boolean existsByEmail(String email);
	
	public boolean existsByPseudonyme(String pseudo);
	
	public Utilisateur findByEmail(String email);
}