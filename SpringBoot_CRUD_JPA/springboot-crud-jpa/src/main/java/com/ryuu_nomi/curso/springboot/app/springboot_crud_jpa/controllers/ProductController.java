package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.Product;
import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.services.ProductService;

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
    private ProductService service;

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
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        // Product productNew = service.save(product);
        // return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    // modificacion
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        // Product productDB = service.findById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Product product = new Product();
        product.setId(id);
        Optional<Product> productOptional = service.delete(product);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
