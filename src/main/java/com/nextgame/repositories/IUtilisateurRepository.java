package com.nextgame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextgame.entities.Utilisateur;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	
	public boolean existsByEmail(String email);
	
	public Utilisateur findByEmail(String email);
}