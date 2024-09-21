package com.nextgame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextgame.entities.Developpeur;

@Repository
public interface IDeveloppeurRespository extends JpaRepository<Developpeur, Long>{
}