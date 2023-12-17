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
        menuList.add(new Menu(1, "Thêm mới thí sinh."));
        menuList.add(new Menu(2, "Hiển thị thông tin của thí sinh và khối thi của thí sinh"));
        menuList.add(new Menu(3, "Tìm kiếm theo số báo danh hoặc họ tên thí sinh."));
        menuList.add(new Menu(4, "Chức năng cập nhật thông tin thí sinh"));
        menuList.add(new Menu(5, "Xóa thông tin thí sinh khỏi danh sách"));
        menuList.add(new Menu(6, "Thêm trường thi."));
        menuList.add(new Menu(7, "Cập nhật lịch thi cho học sinh."));
        menuList.add(new Menu(8, "Hiển thị danh sách thí sinh theo khối thi"));
        menuList.add(new Menu(9, "Xuất danh sách thí sinh ra file CSV"));
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
