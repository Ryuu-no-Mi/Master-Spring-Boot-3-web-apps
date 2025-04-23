package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.services.ProductServiceImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String> {

    @Autowired
    private ProductServiceImpl service;

    // @Override
    // public boolean isValid(String value, ConstraintValidatorContext context) {
        // if(service==null)
        // {
        //     return true;
        // }
    //     return !service.existsBySku(value);
    // }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return true;
    }
}
