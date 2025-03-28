package com.ryuunomi.springboot.app.sprinboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ryuunomi.springboot.app.sprinboot_di.models.Product;
import com.ryuunomi.springboot.app.sprinboot_di.repositories.IProductRepository;

//El services se encarga de manejar las transacciones
// @Component
@Service
public class ProductServiceImpl implements IProductService {

  // El repositorio es la capa de acceso a ddatos, donde se encuentra la
  // información
  // private ProductRepositoryImpl repository = new ProductRepositoryImpl();

  /**
   * El contenedor de Spring nos pasa la instancia (creada con el patron
   * singlenton de fondo)
   * El principo hollywood: no nos llames nosotros te llamaremos
   * Todo lo manjea el controlador
   */

  /*
   * @Autowired
   * private ProductRepositoryImpl repository;
   * 
   * Al inyectar con la interfaz queda mas claro
   */
  private IProductRepository repository;

  @Value("${config.price.tax}")
  private Double tax;

  // TAmbien se lo podemmos pasar al constructor o no ya que springboot lo hace
  // por nosotros
  // @Qualifier("nombre de la clase a inyectar podria ser otra que no sea la que
  // tenga la anotacion @primary")
  // nombre de la clase, emepzando en minuscula
  // @Autowired
  // public ProductServiceImpl(@Qualifier("productRepositoryImpl")
  // IProductRepository repository) {
  // this.repository = repository;
  // }

  @Autowired
  public ProductServiceImpl(IProductRepository repository) {
    this.repository = repository;
  }

  public List<Product> findAll() {

    // Estamos "trabajando con los productos" y luego volvemos tomar una lista
    // El ".map" crea nuevas instancias de la lsita de productos
    // System.out.println(enviroment.getProperty("config.price.tax", Double.class));
    return repository.findAll().stream().map(p -> {
      Double precioImpuesto = p.getPrice() * tax;
      // Creamoa un objeto producto y
      // Product newProduct = new Product(p.getId(), p.getName(),
      // precioImpuesto.longValue());
      Product newProduct = (Product) p.clone();
      newProduct.setPrice(precioImpuesto.longValue());
      return newProduct;
    }).collect(Collectors.toList());
    // toList(); hace lo mismo
  }

  public Product findById(Long id) {
    return repository.findById(id);
  }

}
