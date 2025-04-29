import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { ProductComponent } from './app/products/components/product/product.component';

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));

  // bootstrapApplication(ProductComponent, appConfig).catch((err) =>
  //   console.error(err)
  // );
