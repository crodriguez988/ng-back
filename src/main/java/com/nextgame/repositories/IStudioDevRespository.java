package com.nextgame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextgame.entities.StudioDev;

@Repository
public interface IStudioDevRespository extends JpaRepository<StudioDev, Long>{
}