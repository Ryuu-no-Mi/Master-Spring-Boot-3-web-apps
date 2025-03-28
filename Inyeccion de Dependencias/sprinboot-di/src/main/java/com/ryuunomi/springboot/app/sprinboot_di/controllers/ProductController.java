package com.ryuunomi.springboot.app.sprinboot_di.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.ryuunomi.springboot.app.sprinboot_di.models.Product;
import com.ryuunomi.springboot.app.sprinboot_di.services.IProductService;
import com.ryuunomi.springboot.app.sprinboot_di.services.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ProductController {

  // si lo dejamos como atributo cada vez que se ejecute findAll(), subira un %
  // los precios en cada llamada, un problema de mutabilidad
  // private ProductServiceImpl service = new ProductServiceImpl();

  /**
   * No creamos ni lalmamos a una instancia sino que Springboot lo hace por
   * nosotros
   */

  /*
   * @Autowired
   * private ProductServiceImpl service;
   * 
   * Al implementarlo con la interfaz queda mas
   */
  @Autowired
  private IProductService service;

  @GetMapping()
  public List<Product> list() {
    // Si dejamos la declaracio en el metodo, ya no pasa, pero tenemos duplicidad de
    // codigo
    // ProductServiceImpl service = new ProductServiceImpl();
    return service.findAll();
  };

  @GetMapping("/{id}")
  public Product show(@PathVariable Long id) {
    // ProductServiceImpl service = new ProductServiceImpl();
    return service.findById(id);
  };

}
