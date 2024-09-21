package com.nextgame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextgame.entities.Franchise;

@Repository
public interface IFranchiseRepository extends JpaRepository<Franchise, Long>{
}