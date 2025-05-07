import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from './model/product';
import { CommonModule } from '@angular/common';
import { FormComponent } from "../form/form.component";

@Component({
  selector: 'app-product',
  imports: [CommonModule, FormComponent],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
})
export class ProductComponent implements OnInit {
  // propiedad para almacenar los productos
  products: Product[] = [];

  productSelected: Product = new Product();
  // productSelected: Product = { id: 0, name: '', price: 0, description: '' };

  // private service: ProductService; la forma correcta es inyextarlo en el contructor
  constructor(private service: ProductService) {}

  //metodos del ciclo de vida de angular
  // .subscribe() es un método que se usa para escuchar o consumir los datos que emite un Observable.
  // Conéctate a este Observable, y cuando tengas los datos (productos), ejecútame esta función
  ngOnInit(): void {
    this.service.findAll().subscribe((products) => (this.products = products));
  }

  addProduct(product: Product):void {
    // forma mutable
    // product.id = new Date().getTime(); // Generar un ID único para el producto
    // this.products.push(product);
    
    if (product.id > 0) { // si el id es mayor a 0, significa que el producto ya existe y se va a actualizar
      this.products = this.products.map((p) => {
        if (p.id === product.id) {
          return { ...product };
        }
        return p;
      });
    } else {
      // forma inmutable
      this.products = [
        ...this.products,
        { ...product, id: new Date().getTime() },
      ];
    }

    this.productSelected = new Product(); // Limpiar el formulario después de agregar o actualizar un producto
  }
  
  onRemoveProduct(id: number): void {
    // forma mutable
    this.products = this.products.filter((product) => product.id !== id);
    // forma inmutable
  
  }

  onUpdateProduct(productRow: Product): void {
    // this.productSelected = productRow;
    // realizamos una copia del producto seleccionado para evitar mutaciones
    this.productSelected = { ...productRow };
  };

}
