package com.hackaton.waste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackaton.waste.entity.Container;

public interface ContainerRepository extends JpaRepository <Container, Integer>{

}
