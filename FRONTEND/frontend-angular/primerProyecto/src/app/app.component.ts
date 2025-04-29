import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root2',
  imports: [CommonModule ,RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Hola Mundo ';
  subtitle: string = 'Desde Angular 18';
  enabled: boolean = false;
  courses: String[] = ["Spring Boot", "Java 18", "React", "Angular", "Amazon AWS"];


  setEnabled(): void {
    this.enabled = this.enabled ? false : true;
    console.log("Has realizado click en el boton");
  }
}
