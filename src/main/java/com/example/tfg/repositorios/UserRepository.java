package com.example.tfg.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tfg.modelos.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
