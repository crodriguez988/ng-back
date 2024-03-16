package com.nextgame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextgame.entities.Genre;

@Repository
public interface IGenreRespository extends JpaRepository<Genre, Long>{
}