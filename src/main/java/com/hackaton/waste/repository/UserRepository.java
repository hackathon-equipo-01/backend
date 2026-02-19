package com.hackaton.waste.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackaton.waste.entity.User;
import com.hackaton.waste.entity.enums.Role;

public interface UserRepository extends JpaRepository<User, Integer>{

    List<User> findByRole(Role role);

    Optional<User> findByEmail(String email);
}
