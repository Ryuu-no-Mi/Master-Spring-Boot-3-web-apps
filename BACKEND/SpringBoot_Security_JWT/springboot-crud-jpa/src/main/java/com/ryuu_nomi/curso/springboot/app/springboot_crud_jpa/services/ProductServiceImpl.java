package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.Product;
import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    // Indica que una transacción es solo de lectura y no realizará ninguna  modificación en la base de datos
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }
    
    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            Product productDB = productOptional.orElseThrow();
            
            productDB.setName(product.getName());
            productDB.setDescription(product.getDescription());
            productDB.setPrice(product.getPrice());

            return Optional.of(repository.save(productDB));
        }

        return productOptional;
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        productOptional.ifPresent(productDB -> {
            repository.delete(productDB);
        });

        // solo para mostrar al usuario que ha sido borrado
        return productOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }
}
