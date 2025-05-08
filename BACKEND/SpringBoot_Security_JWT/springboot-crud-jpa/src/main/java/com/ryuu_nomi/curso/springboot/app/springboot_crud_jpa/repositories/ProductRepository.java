package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.Product;

@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:4200"}) // Cambia el puerto según tu configuración de frontend
@RepositoryRestResource(path = "products")  // Cambia el nombre del endpoint a /api/products
//                 .requestMatchers(HttpMethod.GET, "/api/users/{id}").permitAll()
public interface ProductRepository extends CrudRepository<Product, Long> {
    // boolean existsBySku(String sku);
}


