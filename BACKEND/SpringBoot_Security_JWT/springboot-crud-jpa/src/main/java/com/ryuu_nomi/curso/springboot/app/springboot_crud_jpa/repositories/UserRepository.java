package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
