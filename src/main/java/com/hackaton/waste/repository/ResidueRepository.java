package com.hackaton.waste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackaton.waste.entity.Residue;

public interface ResidueRepository extends JpaRepository<Residue, Integer>{

}
