package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.services.IUserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsernamee, String> {
    
    @Autowired
    private IUserService iservice;

    @Override
    public void initialize(ExistsByUsernamee constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        System.out.println("Validando el nombre de usuario: " + username + ", instancia del validador: " + this
                + ", servicio es: " + iservice);
        if (iservice == null) {
            System.out.println("¡¡¡ EL SERVICIO ES NULO !!! en isValid");
        }
        return iservice == null || !iservice.existsByUsername(username);
    }
}
