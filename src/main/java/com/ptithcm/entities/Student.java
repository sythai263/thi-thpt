package com.ptithcm.entities;

import com.google.gson.Gson;

public class Student {

  private Long id;
  private String name;


  public Student() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toJsonString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }

}
