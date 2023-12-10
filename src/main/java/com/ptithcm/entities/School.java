package com.ptithcm.entities;

import com.google.gson.Gson;

public class School {

  private Long id;
  private String name;
  private Boolean isExit;

  private String address;

  public School() {
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public Boolean getExit() {
    return isExit;
  }

  public void setExit(Boolean exit) {
    isExit = exit;
  }

  public String toJsonString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
