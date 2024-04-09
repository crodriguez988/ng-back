package com.nextgame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextgame.entities.Plateforme;

@Repository
public interface IPlateformeRepository extends JpaRepository<Plateforme, Long>{
}