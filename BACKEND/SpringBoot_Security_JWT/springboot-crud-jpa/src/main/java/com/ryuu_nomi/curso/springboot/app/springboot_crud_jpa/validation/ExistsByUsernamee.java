package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistsByUsernameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUsernamee {

	String message() default "este username ya esta siendo utilizado por otro usuario";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}