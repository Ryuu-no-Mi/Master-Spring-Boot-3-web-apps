package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.services;

import java.util.List;
import java.util.Optional;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.Product;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> delete(Product product);
}
