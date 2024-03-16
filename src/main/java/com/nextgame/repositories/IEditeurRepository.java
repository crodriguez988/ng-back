package com.nextgame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextgame.entities.Editeur;

public interface IEditeurRepository extends JpaRepository<Editeur, Long> {
}