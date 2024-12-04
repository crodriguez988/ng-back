package com.nextgame.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nextgame.entities.JeuUtilisateur;

@Repository
public interface IJeuUtilisateurRepository extends JpaRepository<JeuUtilisateur, Long> {
	
	@Query(value = "SELECT ju.id, ju.id_utilisateur, ju.id_jeu, ju.note, ju.date_ajout, ju.statut_jeu, j.nom AS nomJeu, ju.date_termine, GROUP_CONCAT(p.nom SEPARATOR ', ') AS plateformes " +
            "FROM jeu_utilisateur ju " +
            "JOIN jeu j ON ju.id_jeu = j.id " +
            "JOIN jeu_plateforme pj ON j.id = pj.id_jeu " +
            "JOIN plateforme p ON pj.id_plateforme = p.id " +
            "WHERE ju.id_utilisateur = :idUtilisateur " +
            "GROUP BY ju.id", nativeQuery = true)
	List<Object[]> getAllJeuxUtilisateur(@Param("idUtilisateur") Long idUtilisateur);
}