package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
    
}