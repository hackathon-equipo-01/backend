package com.hackaton.waste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackaton.waste.entity.WasteType;

public interface WasteTypeRepository extends JpaRepository<WasteType, Integer>{

    public boolean existsByName(String name);
}
