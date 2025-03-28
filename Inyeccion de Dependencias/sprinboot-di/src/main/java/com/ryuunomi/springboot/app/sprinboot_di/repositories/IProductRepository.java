package com.ryuunomi.springboot.app.sprinboot_di.repositories;

import java.util.List;

import com.ryuunomi.springboot.app.sprinboot_di.models.Product;

public interface IProductRepository {

    List<Product> findAll();

    Product findById(Long id);

}
