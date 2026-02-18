package com.hackaton.waste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackaton.waste.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
