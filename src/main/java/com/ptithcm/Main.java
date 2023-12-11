package com.ptithcm;

import com.ptithcm.common.Menu;
import com.ptithcm.entities.Student;
import com.ptithcm.services.StudentService;
import pl.mjaron.etudes.Table;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static StudentService studentService = new StudentService();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        clear();
        int selection = 1;
        while (selection != 0) {
            selection = getSelection();
            switch (selection) {
                case 1 -> addStudent();
                case 2 -> getAllStudent();
                case 3 -> getStudentByName();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();

            }
            clear();
        }
        System.out.println("Bye bye");
        scanner.close();
    }

    public static Student insertStudent() {
        System.out.print("Nhập tên sv: ");
        String name = scanner.nextLine();

        System.out.print("Nhập lớp sv: ");
        String studentClass = scanner.nextLine();

        System.out.print("Nhập mã trường: ");
        Long schoolId = scanner.nextLong();

        System.out.print("Nhập priority: ");
        Integer priority = scanner.nextInt();

        // Tiêu diệt dấu Enter còn lại trong bộ đệm
        scanner.nextLine();

        String groupSubject;
        while (true) {
            System.out.print("Nhập khối (A, B hoặc C): ");
            groupSubject = scanner.nextLine();
            if ("A".equals(groupSubject) || "B".equals(groupSubject) || "C".equals(groupSubject)) {
                break;
            } else {
                System.out.println("Khối không hợp lệ. Vui lòng nhập lại.");
            }
        }

        return new Student(name, studentClass, schoolId, groupSubject, priority);
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

    public static void addStudent() throws IOException {
        studentService.addStudent(insertStudent());
        askContinue();
    }

    public static void updateStudent() throws IOException {
        System.out.print("Nhập mã sinh viên cần cập nhật: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        List<Student> students = studentService.getStudentByName(id.toString());
        if (students.isEmpty()) {
            System.out.println("Không tìm thấy sinh viên có mã: " + id);
        } else {
            Student student = insertStudent();
            student.setId(id);

            studentService.updateStudent(student);
        }
        askContinue();
    }

    public static void deleteStudent() throws IOException {
        System.out.print("Nhập mã sinh viên cần xoá: ");
        Long id = scanner.nextLong();

        List<Student> students = studentService.getStudentByName(id.toString());
        if (students.isEmpty()) {
            System.out.println("Không tìm thấy sinh viên có mã: " + id);
        } else {
            Student student = new Student();
            student.setId(id);

            studentService.deleteStudent(student);
        }
        scanner.nextLine();
        askContinue();
    }

    public static void getStudentByName() throws IOException {
        String name = "";
        System.out.print("Nhập tên hoặc mã sinh viên cần tìm: ");
        name = scanner.nextLine();
        List<Student> students = studentService.getStudentByName(name);
        if (students.isEmpty()) {
            System.out.println("Không tìm thấy sinh viên có mã/tên: " + name);
            askContinue();
        } else {
            printTableStudent(students);
        }
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