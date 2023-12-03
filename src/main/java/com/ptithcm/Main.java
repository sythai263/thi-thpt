package com.ptithcm;

import com.ptithcm.common.Menu;
import com.ptithcm.entities.Student;
import com.ptithcm.services.StudentService;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import pl.mjaron.etudes.Table;

public class Main {

  static StudentService studentService = new StudentService();
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    clear();
    int selection = 1;
    while (selection != 0) {
      selection = getSelection();
      switch (selection) {
        case 1 -> getAllStudent();
        case 2 -> getStudentByName();
      }
      clear();
    }
    System.out.println("Bye bye");
    scanner.close();
  }

  public static void printTableStudent(List<Student> students) throws IOException {
    clear();
    Table.render(students, Student.class).run();
    askContinue();
  }

  public static void getAllStudent() throws IOException {
    List<Student> list = studentService.getStudents();
    printTableStudent(list);
  }

  public static int getSelection() {
    int selection = 0;
    List<Menu> menu = Menu.getMenuInstance();
    Table.render(menu, Menu.class).run();

    selection = scanner.nextInt();
    scanner.nextLine();

    return selection;
  }

  public static void getStudentByName() throws IOException {
    String name = "";
    System.out.println("Nhập tên sv cần tìm: ");
    name = scanner.nextLine();
    List<Student> students = studentService.getStudentByName(name);
    printTableStudent(students);

  }

  private static void clear() throws IOException {
    for (int i = 0; i < 10; i++) {
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
    }
  }

  private static void askContinue() throws IOException {
    System.out.println("Nhấn enter để tiếp tục : ");
    scanner.nextLine();
  }

}