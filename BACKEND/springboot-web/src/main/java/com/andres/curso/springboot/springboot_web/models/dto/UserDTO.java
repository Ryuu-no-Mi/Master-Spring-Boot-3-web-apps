package com.andres.curso.springboot.springboot_web.models.dto;

import com.andres.curso.springboot.springboot_web.models.User;

public class UserDTO {

  /**
   * Un DTO es un POJO (Plain Object Java) es decir, clases Java simples que no
   * extienden ni implementan otras clases o interfaces específicas.
   */
  private String title;
  private User user;

  public UserDTO() {
  }

  public UserDTO(String title, User user) {
    this.title = title;
    this.user = user;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
