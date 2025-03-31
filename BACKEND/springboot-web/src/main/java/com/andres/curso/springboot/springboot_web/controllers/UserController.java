package com.andres.curso.springboot.springboot_web.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.andres.curso.springboot.springboot_web.models.User;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  /*
   * El model sirve para pasar datos a la vista
   * 
   * Usando el objeto Model:
   * 
   * El objeto Model es una interfaz proporcionada por Spring que permite agregar
   * atributos que serán accesibles en la vista.
   * 
   * Se usa el método addAttribute para añadir datos al modelo.
   * 
   * Los atributos añadidos con addAttribute se pueden acceder directamente en la
   * vista de Thymeleaf.
   */

  /**
   * El método 'details' utiliza el objeto Model para pasar datos a la vista.
   *
   * @param model El objeto Model que se utiliza para agregar atributos que
   *              estarán disponibles en la vista.
   * @return El nombre de la vista 'details' que será renderizada.
   */
  @GetMapping("/details")
  public String details(Model model) {
    User user = new User("Pepe", "Jimenez");
    model.addAttribute("title", "Hola Mundo desde Spring Boot");
    model.addAttribute("user", user);
    // model.addAttribute("name", "Pepe");
    // model.addAttribute("lastname", "Juejl");

    return "details";
  }

  /*
   * Usando un Map:
   * 
   * Un Map (generalmente un Map<String, Object>) también puede ser utilizado para
   * pasar datos a la vista.
   * El método put se utiliza para añadir datos al mapa.
   * 
   * Los atributos añadidos al mapa se pueden acceder en la vista de Thymeleaf de
   * la misma manera que los añadidos a través de Model.
   * 
   * Ambos métodos permiten pasar datos a la vista, pero el uso de Model es más
   * común y
   * preferido en la mayoría de las aplicaciones de Spring debido a su simplicidad
   * y claridad.
   */

  /**
   * El método 'details2' utiliza un Map para pasar datos a la vista.
   *
   * @param model Un mapa de atributos que estarán disponibles en la vista.
   * @return El nombre de la vista 'details2' que será renderizada.
   */
  @GetMapping("/details2")
  public String details(Map<String, Object> model) {
    model.put("title2", "Hola Mundo desde Spring Boot2WS");
    model.put("name2", "Pepe");
    model.put("lastname2", "Juejl");

    return "details2";
  }

  @GetMapping("/list")
  public String list(ModelMap modelMap) {
    // List<User> users = new ArrayList<>();

    // modelMap.addAttribute("users", users);
    modelMap.addAttribute("title", "Listado de Usuarios");
    return "list";
  }

  /*
   * Reutilizacion de la lista users atraves de la anotacion ModelAttribute
   */
  @ModelAttribute("users")
  public List<User> userModel() {
    // realizando un for ecah
    /*
     * List<User> users = Arrays.asList(
     * new User("genoveoa", "hjksdf", "ghjksdf@sda.es"),
     * new User("lalo", "hjksdf"),
     * new User("jorge", "hjksdf"),
     * new User("genaro", "gege", "vivaespaña@sda.es"));
     */
    return Arrays.asList(
        new User("genoveoa", "hjksdf", "ghjksdf@sda.es"),
        new User("lalo", "hjksdf"),
        new User("jorge", "hjksdf"),
        new User("genaro", "gege", "vivaespaña@sda.es"));
  }

}
