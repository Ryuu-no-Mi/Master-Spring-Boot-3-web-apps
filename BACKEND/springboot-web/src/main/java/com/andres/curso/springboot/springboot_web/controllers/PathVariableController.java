package com.andres.curso.springboot.springboot_web.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.andres.curso.springboot.springboot_web.models.User;
import com.andres.curso.springboot.springboot_web.models.dto.ParamDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/var")

public class PathVariableController {

  @GetMapping("/baz/{message}")
  public ParamDTO baz(@PathVariable String message) {
    ParamDTO param = new ParamDTO();
    param.setMessage(message);
    return param;
  }

  // pasando varias path variables, se podia hacer con un DTO es por variar
  @GetMapping("/mix/{product}/{id}")
  public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {
    Map<String, Object> json = new HashMap<>();
    json.put("product", product);
    json.put("id", id);
    return json;
  }

  @PostMapping("/create")
  public User create(@RequestBody User user) {
    // hacer algo con el usuario, por ejemplo un save en BDD
    user.setName(user.getName().toUpperCase());
    return user;
  }

}
