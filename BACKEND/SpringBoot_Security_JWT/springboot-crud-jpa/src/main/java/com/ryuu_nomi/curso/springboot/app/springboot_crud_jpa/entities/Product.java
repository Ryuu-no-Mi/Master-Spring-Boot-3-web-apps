package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.validation.IsExistsDb;
import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.validation.IsRequired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@IsRequired
    //@IsExistsDb
    //private String sku;

    //Solo para los string, validamos que no sea vacio
    // Validamos el tamaño del string 
    //@NotBlank(message = "{NotBlank.product.name}")
    @IsRequired(message = "{IsRequired.product.name}")
    @Size(min = 3, max = 20, message = "{Size.product.name}")
    private String name;

    //Pongo minimo 1 por ser entero, lo suyo seria un double, y procurar qu eningun producto sea menor que 0.1d
    @Min(value = 1, message = "{Min.product.price}")
    @NotNull(message = "{NotNull.product.price}")
    private Integer price;

    @IsRequired
    private String description;

    @CreationTimestamp
    @Column(name= "date",  updatable= false)
    private LocalDateTime date;

    public Product() {
    }

    public Product(Long id, String name, Integer price, String description, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.date = date;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // public String getSku() {
    //     return sku;
    // }

    // public void setSku(String sku) {
    //     this.sku = sku;
    // }

}
