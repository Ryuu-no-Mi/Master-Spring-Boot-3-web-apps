package com.andres.curso.springboot.springboot_web.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andres.curso.springboot.springboot_web.models.User;
import com.andres.curso.springboot.springboot_web.models.dto.UserDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// @RestController es una combinación de las anotaciones @Controller y @ResponseBody.
@RestController
// @RequestMapping Define una ruta base
@RequestMapping("/api")
public class UserRestController {

  /* Queremos devolver un json no el cuerpo de la vista */
  /**
   * @GetMapping("/detailsRest")
   * public String details() {
   * Map<String, Object> body = new HashMap<>();
   * body.put("title", "Hola Mundo desde Spring Boot");
   * body.put("name", "Pepe");
   * body.put("lastname", "Juejl");
   * 
   * return "details";
   * }
   * 
   */

  /*
   * Un RestController en Spring Boot se utiliza para crear servicios RESTful,
   * donde las respuestas son generalmente en formato JSON o XML, en lugar de
   * devolver vistas HTML como lo haría un Controller tradicional.
   * 
   * En tu ejemplo, has creado un UserRestController que devuelve un JSON en
   * respuesta a una solicitud HTTP GET en el endpoint /detailsRest.
   */

  /**
   * Este método maneja las solicitudes GET a "/detailsRest" y devuelve un mapa
   * que se convierte automáticamente a JSON.
   *
   * @return Un mapa con datos que serán convertidos a JSON.
   */

  /*
   * El método crea un objeto HashMap que contiene varios pares clave-valor.
   * Estos pares clave-valor representan los datos que se devolverán en la
   * respuesta.
   * Dado que el controlador está anotado con @RestController, Spring convierte
   * automáticamente el Map en una respuesta JSON.
   */
  @GetMapping(path = "/detailsRest")
  public Map<String, Object> details() {

    Map<String, Object> body = new HashMap<>();
    body.put("title", "Hola Mundo desde Spring Boot desde un Restful");
    body.put("name", "Pepe");
    body.put("lastname", "Juejl");

    return body;
  }

  /*
   * Devuelve un mapa que incluye un objeto User y otros datos, convirtiéndose
   * automáticamente a JSON.
   */
  /*
   * Crea una instancia de la clase User con datos específicos.
   * Crea un HashMap y añade un título y el objeto User al mapa.
   * Spring convierte el Map en una respuesta JSON debido a la
   * anotación @RestController.
   */

  /**
   * Este método maneja las solicitudes GET a "/detailsRestModel" y devuelve un
   * mapa que incluye un objeto User y otros datos, que serán convertidos a JSON.
   *
   * @return Un mapa con datos que serán convertidos a JSON, incluyendo un objeto
   *         User.
   */
  @GetMapping(path = "/detailsRestModel-map")
  public Map<String, Object> detailsModel() {
    User user = new User("Pepe", "Jimenez");
    // Lo suyo seria usar un DTO (Data Transfer Object)
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Hola Mundo desde Spring Boot desde un Restful");
    body.put("user", user);

    return body;
  }

  @GetMapping(path = "/detailsDTO")
  public UserDTO detailsModelDTO() {

    User user = new User("Pepa", "Pignite");
    UserDTO userDto = new UserDTO("Bienvenido a SpringBoot desde un DTO", user);
    // Map<String, Object> body = new HashMap<>();
    // body.put("title", "Hola Mundo desde Spring Boot desde un Restful");
    // body.put("user", user);

    return userDto;
  }

  /*
   * Esta deberia ser una consulta a la BDD
   */
  @GetMapping("/list")
  public List<User> listAll() {

    User user = new User("Pepa", "Pignite");
    User user2 = new User("Pepa2", "Pignite2");
    User user3 = new User("Pepa33", "Pignite32");

    // Ejemplo usando un Helper
    List<User> userList = Arrays.asList(user, user2, user3);

    // List<User> userList = new ArrayList<>();
    // userList.add(user);
    // userList.add(user2);
    // userList.add(user3);
    return userList;
  }

  @PostMapping("/create")
  public User create(@RequestBody User user) {
    //hacer un save en la bd
    //Es mas por hacer algo con el nombre cuando nos de un 200
      user.setName(user.getName().toUpperCase());
      return user;
  }
  
}
