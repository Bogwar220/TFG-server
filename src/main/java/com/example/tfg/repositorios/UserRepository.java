package com.example.tfg.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tfg.modelos.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer>{

}
