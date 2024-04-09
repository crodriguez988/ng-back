package com.nextgame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextgame.entities.Jeu;

@Repository
public interface IJeuRepository extends JpaRepository<Jeu, Long>{
}