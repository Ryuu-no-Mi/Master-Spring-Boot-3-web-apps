package com.ryuunomi.springboot.app.sprinboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ryuunomi.springboot.app.sprinboot_di.models.Product;

// @Primary es para poner el repository por defecto
@Repository
public class ProductRepositoryFoo implements IProductRepository {

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Monitro Asus 27", 600L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id, "Monitro Asus 27", 600L);
    }

}
