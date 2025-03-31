package com.andres.curso.springboot.springboot_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeControl {

    /*
     * 1. redirect:
     * Hace que el navegador realice una nueva solicitud HTTP con una nueva URL,
     * cambiando la barra de direcciones
     */

    /*
     * 2. forward
     * El servidor maneja la solicitud internamente y no cambia la URL en el
     * navegador del cliente.
     */
    //Para redirigir al home 
    @GetMapping({ "", "/", "/home"})
    public String home() {
        return "redirect:/list";
        //return "forward:/list";
    }
    
}
