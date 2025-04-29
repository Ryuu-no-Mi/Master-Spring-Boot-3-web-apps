import { Component } from '@angular/core';
import { Product } from '../product/model/product';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'product-form',
  imports: [FormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  // product: Product = new Product();
  product: Product = { id: 0, name: '', price: 0, description: '' };
}
