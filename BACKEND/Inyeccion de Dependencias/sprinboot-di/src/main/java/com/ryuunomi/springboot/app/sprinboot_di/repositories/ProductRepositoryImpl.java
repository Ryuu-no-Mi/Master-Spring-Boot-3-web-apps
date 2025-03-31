package com.ryuunomi.springboot.app.sprinboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.ryuunomi.springboot.app.sprinboot_di.models.Product;

// @ComponentÇ
// @RequestScope
// Solo va existir durante la peticvion despues se destruye, 
// deja de ser singlenton
// @SessionScope
// Solo va existir durante la session, varias peticiones, tener el navegador
// abierto/postman y luego cerrar
@Primary
@Repository

public class ProductRepositoryImpl implements IProductRepository {
  private List<Product> data;

  public ProductRepositoryImpl() {
    this.data = Arrays.asList(
        new Product(0L, "Memoria corsair vengace", 88L),
        new Product(1L, "Intel core i9", 350L),
        new Product(2L, "RTX 4080", 2899L),
        new Product(3L, "Teclado newskill", 54L),
        new Product(4L, "Teclado newskill V3.1", 64L));
  }

  @Override
  public List<Product> findAll() {
    return data;
  }

  @Override
  public Product findById(Long id) {

    // Forma basica de hacerlo
    /*
     * Es preferible utilizar equals en lugar de == para comparar objetos en Java,
     * ya que equals verifica el valor y == verifica la referencia en memoria.
     */
    for (Product product : data) {
      if (product.getId().equals(id)) {
        return product;
      }
    }

    return null;
    /*
     * lanza una excepcion sino lo encuentra
     */

    // return data.stream().filter(p
    // ->p.getId().equals(id)).findFirst().orElseThrow();
    /*
     * Si no lo encuentra da null
     */
    // return data.stream().filter(p
    // ->p.getId().equals(id)).findFirst().orElse(null);
  }
}
