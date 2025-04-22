package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//Clse para sobreescribir el constructor de SimpleGrantedAuthority
public abstract class SimpleGrantedAuthorityJsonCreator {

    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role) {
    }
    
}
