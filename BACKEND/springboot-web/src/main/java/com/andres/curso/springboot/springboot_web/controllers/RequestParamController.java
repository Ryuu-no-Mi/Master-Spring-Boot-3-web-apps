package com.andres.curso.springboot.springboot_web.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.andres.curso.springboot.springboot_web.models.dto.ParamDTO;
import com.andres.curso.springboot.springboot_web.models.dto.ParamMixDTO;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/params")
public class RequestParamController {

  @GetMapping("/foo")
  public ParamDTO foo(@RequestParam String message) {
    ParamDTO param = new ParamDTO();
    param.setMessage(message);
    return param;
  }

  // El request param no es obligatorio y tendra un valor por defecto
  @GetMapping("/foo2")
  public ParamDTO foo2(@RequestParam(required = false, defaultValue = "hoal springboot") String message) {
    ParamDTO param = new ParamDTO();
    // param.setMessage(message != null ? message : "Hola Springboot");
    param.setMessage(message);
    return param;
  }

  // Vamos a pasar por argumento mas de un parametro
  @GetMapping("/bar")
  public ParamMixDTO bar(@RequestParam String txt, @RequestParam Integer code) {
    ParamMixDTO params = new ParamMixDTO();
    params.setMessage(txt);
    params.setCode(code);
    return params;
  }

  @GetMapping("/request")
  public ParamMixDTO request(HttpServletRequest request) {
    ParamMixDTO params = new ParamMixDTO();

    // request.getParameter("code") siempre va a recibir un string
    // puede ocurrir que se inserte un dato no numerico o null
    // params.setCode(Integer.parseInt(request.getParameter("code")));
    Integer code = 0;
    try {
      code = Integer.parseInt(request.getParameter("code"));
    } catch (NumberFormatException e) {
    }
    params.setCode(code);
    params.setMessage(request.getParameter("message"));
    return params;
  }
}
