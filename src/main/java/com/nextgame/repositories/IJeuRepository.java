package com.nextgame.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nextgame.dtos.JeuxDetailsDTO;
import com.nextgame.entities.Jeu;

@Repository
public interface IJeuRepository extends JpaRepository<Jeu, Long>{
	
	public List<Jeu> findByGenresId (Long id);
	
	public List<Jeu> findByFranchiseId (Long idFranchise);
	
	public List<Jeu> findByDeveloppeursId (Long id);
	
	@Query("SELECT j FROM Jeu j WHERE YEAR(j.dateSortie) = :annee")
	public List<Jeu> findByAnneSortie (@Param("annee") int annee);
	
	@Query(value = "SELECT j.nom, " +
            "(SELECT d.nom FROM jeu_developpeur jd " +
            "JOIN developpeur d ON jd.id_developpeur = d.id_developpeur " +
            "WHERE jd.id_jeu = j.id_jeu ORDER BY d.nom ASC LIMIT 1) AS developpeur, " +
            "j.date_sortie, " +
            "uj.statut_jeu, " +
            "COALESCE(AVG(uj.note), 0) AS note " + //COALESCE permet de remplacer un NULL par une valeur par défaut
            "FROM jeu j " +
            "LEFT JOIN jeu_utilisateur uj ON uj.id_jeu = j.id_jeu " +
            "GROUP BY j.id_jeu, j.nom, j.date_sortie, uj.statut_jeu",
    nativeQuery = true)
public List<JeuxDetailsDTO> findAllJeuxDetails();
}