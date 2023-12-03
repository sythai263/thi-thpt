package com.ptithcm.common;

import java.util.ArrayList;
import java.util.List;

public class Menu {

  private int Key;
  private String Description;

  public Menu() {
  }

  public Menu(int id, String name) {
    this.Key = id;
    this.Description = name;
  }

  public static List<Menu> getMenuInstance() {
    List<Menu> menuList = new ArrayList<>();
    menuList.add(new Menu(1, "In danh sách sinh viên"));
    menuList.add(new Menu(2, "Tìm sinh viên theo mã"));
    menuList.add(new Menu(0, "Thoát"));
    return menuList;

  }

  public int getKey() {
    return Key;
  }

  public void setKey(int key) {
    this.Key = key;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    this.Description = description;
  }
}
