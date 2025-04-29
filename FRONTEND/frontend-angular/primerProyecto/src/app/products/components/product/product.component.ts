import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from './model/product';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product',
  imports: [CommonModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
})
  
export class ProductComponent implements OnInit {
  // propiedad para almacenar los productos
  products: Product[] = [];
  // private service: ProductService; la forma correcta es inyextarlo en el contructor
  constructor(private service: ProductService) {}

  //metodos del ciclo de vida de angular
  // .subscribe() es un método que se usa para escuchar o consumir los datos que emite un Observable.
  // Conéctate a este Observable, y cuando tengas los datos (productos), ejecútame esta función
  ngOnInit(): void {
    this.service.findAll().subscribe((products) => this.products = products);
  }
}
