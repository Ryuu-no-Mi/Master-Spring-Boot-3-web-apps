import { Injectable } from '@angular/core';
import { Product } from '../components/product/model/product';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private products: Product[] = [
    { id: 1, name: 'Mesa Comedor', description: 'Idonea para una familia de 6 personas', price: 700 },
    { id: 2, name: 'Teclado Mecánnico RKRS', description: 'Se escuha todos y cada unos del tipeo, ajuste modular', price: 200 },
    { id: 3, name: 'Product 3', description: 'Description 3', price: 333.33 },
    { id: 4, name: 'Product 4', description: 'Description 4', price: 400 },
    { id: 5, name: 'Product 5', description: 'Description 5', price: 999.68 }
  ];
  constructor() { }

  findAll(): Observable<Product[]> {
    //of es un operador de rxjs que permite crear un observable a partir de un valor
    return of(this.products);
  }
}
