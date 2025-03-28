package com.ryuunomi.springboot.app.sprinboot_di.services;

import java.util.List;

import com.ryuunomi.springboot.app.sprinboot_di.models.Product;

public interface IProductService {

    List<Product> findAll();

    Product findById(Long id);

}
