package com.hackaton.waste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hackaton.waste.entity.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer>{

}
