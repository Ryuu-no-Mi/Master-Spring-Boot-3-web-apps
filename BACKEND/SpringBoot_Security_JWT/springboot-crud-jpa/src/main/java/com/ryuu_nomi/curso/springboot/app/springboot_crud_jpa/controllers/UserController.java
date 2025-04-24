package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.User;
import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.services.IUserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping
    public List<User> list() {
        return service.findAll();
    };

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid  @RequestBody User user, BindingResult result) {   
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        user.setAdmin(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
        // user.setAdmin(false);
        // return create(user, result);
    }

    //podriamos reutilizar validation creando uan clase helper
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        return ResponseEntity.badRequest().body(errors);
    }
    
}
