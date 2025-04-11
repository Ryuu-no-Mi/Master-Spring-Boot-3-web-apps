package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.Product;
import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.services.IProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping
    public List<Product> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        //return service.findById(id).get();
        Optional<Product> productOptional = service.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // Agregar
    // el @ valid debe ir en el request body <Product>
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        // Product productNew = service.save(product);
        // return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    // modificacion
    // el @ valid debe ir en el request body <Product>
    //El BindingResult debe esta al lado del objeto a validar
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
        // Product productDB = service.findById(id);
        //return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id,product).get());

        if (result.hasFieldErrors()) {
            return validation(result);
        }
        
        //Manejando el optionalreturn
        Optional<Product> productOPtional = service.update(id, product);
        if (productOPtional.isPresent()) {

            return ResponseEntity.status(HttpStatus.CREATED).body(productOPtional.orElseThrow());
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> productOptional = service.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // ? es un tipo generico
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        return ResponseEntity.badRequest().body(errors);
    }
}
