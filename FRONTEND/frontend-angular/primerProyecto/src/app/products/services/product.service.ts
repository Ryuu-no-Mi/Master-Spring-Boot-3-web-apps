import { Injectable } from '@angular/core';
import { Product } from '../components/product/model/product';
import { map, Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

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
  private url: string = 'http://localhost:8080/api/products'; // URL de la API
  // private url: string = 'https://jsonplaceholder.typicode.com/posts'; // URL de la API
  constructor(private hhtp: HttpClient) { }

  findAll(): Observable<Product[]> {
    //of es un operador de rxjs que permite crear un observable a partir de un valor
    // return of(this.products);
    // ya no estás usando @RepositoryRestResource o
    // ya estás devolviendo productos como un List < Product > desde un @RestController. 
    // Por lo tanto, la respuesta de tu backend es simplemente un array
    // return this.hhtp.get<Product[]>(this.url).pipe(
    //   map((response: any) => response._embedded.products as Product[]), // Ajusta esto según la estructura de tu respuesta API
    // );
    return this.hhtp.get<Product[]>(this.url);

    // tienes que configurar el httpclient en el main.ts
    // import { HttpClientModule } from '@angular/common/http';
  }

  create(product: Product): Observable<Product> {
    // return this.hhtp.post<Product>(this.url, product).pipe(
    //   map((response: any) => response._embedded.products as Product[]), // Ajusta esto según la estructura de tu respuesta API
    // );
    return this.hhtp.post<Product>(this.url, product);
  }

  update(product: Product): Observable<Product> {
    return this.hhtp.put<Product>(`${this.url}/${product.id}`, product);
  }

}