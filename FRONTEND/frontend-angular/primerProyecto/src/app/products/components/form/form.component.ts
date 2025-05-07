import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../product/model/product';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'product-form',
  imports: [FormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
})
export class FormComponent {
  // product: Product = new Product();
  @Input() product: Product = {
    id: 0, name: '', price: 0, description: ''
  };

  @Output() newProductEvent = new EventEmitter();

  onSubmit(): void {
    this.newProductEvent.emit(this.product);
    // Reset the form after submission
    this.product = { id: 0, name: '', price: 0, description: '' };
    
    // Handle form submission logic here
    console.log('Form submitted:', this.product);

    // You can also call a service to save the product data to a backend API
    // For example:
    // this.productService.saveProduct(this.product).subscribe(response => {
    //   console.log('Product saved:', response);
    throw new Error('Method not implemented.');
  }
}
