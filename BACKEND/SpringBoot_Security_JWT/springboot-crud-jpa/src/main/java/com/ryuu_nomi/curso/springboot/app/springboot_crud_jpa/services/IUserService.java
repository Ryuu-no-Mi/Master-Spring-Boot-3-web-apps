package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.services;

import java.util.List;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.User;

public interface IUserService {
    /**
     * Vamos a devolver una clase User(Entity), si fuera más complejo, podriamos devolver una userDTO
     */
    List<User> findAll();

    User save(User user);
    
    boolean existsByUsername(String username);
}
