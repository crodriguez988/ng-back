package com.nextgame.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nextgame.entities.Jeu;

@Repository
public interface IJeuRepository extends JpaRepository<Jeu, Long>{
	
	public List<Jeu> findByGenresId (Long id);
	
	@Query("SELECT j FROM Jeu j WHERE j.franchise.id = :idFranchise")
	public List<Jeu> findByFranchiseId (Long idFranchise);
	
	public List<Jeu> findByDeveloppeursId (Long id);
	
	@Query("SELECT j FROM Jeu j WHERE YEAR(j.dateSortie) = :annee")
	public List<Jeu> findByAnneSortie (@Param("annee") int annee);
	
}