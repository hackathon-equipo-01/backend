package com.hackaton.waste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackaton.waste.entity.DiscardedWaste;

public interface DiscardedWasteRepository extends JpaRepository<DiscardedWaste, Integer>{
    public List<DiscardedWaste> findByClassroomIdOrderByDateDesc(int classroomId);
    public long countByResidueId(int residueId);
    public long countByContainerId(int containerId);
}
