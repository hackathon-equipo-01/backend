package com.hackaton.waste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackaton.waste.entity.DiscardedWaste;

public interface DiscardedWasteRepository extends JpaRepository<DiscardedWaste, Integer>{

}
