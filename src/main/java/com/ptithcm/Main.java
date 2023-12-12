package com.ptithcm;

import com.ptithcm.common.Menu;
import com.ptithcm.entities.Exam;
import com.ptithcm.entities.School;
import com.ptithcm.entities.Student;
import com.ptithcm.services.ExamService;
import com.ptithcm.services.SchoolService;
import com.ptithcm.services.StudentService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import pl.mjaron.etudes.Table;

public class Main {

    static StudentService studentService = new StudentService();
    static SchoolService schoolService = new SchoolService();
    static ExamService examService = new ExamService();
    static Scanner scanner = new Scanner(System.in);
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

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
                case 6 -> addSchool();
                case 7 -> addExam();

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
        scanner.nextLine();

        System.out.print("Nhập loại ưu tiên: ");
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

    public static void printTableExam(List<Exam> exams) throws IOException {
        clear();
        Table.render(exams, Exam.class).run();
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

    public static void addSchool() throws IOException {
        System.out.print("Nhập tên trường: ");
        String name = scanner.nextLine();

        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();

        School school = new School(name, address);
        int rowsInserted = schoolService.addSchool(school);
        if (rowsInserted > 0) {
            System.out.println("Trường đã được thêm thành công!");
        } else {
            System.out.println("Không thể thêm trường. Vui lòng thử lại.");
        }
        askContinue();
    }

    public static void addExam() throws IOException {
        List<Exam> examList = examService.getAllExam();
        printTableExam(examList);
        Student student = inputAndCheckStudent();
        School school = inputAndCheckSchool();
        Date date = inputAndCheckDueDate();
        String room = inputRoom();
        String subject = inputSubject();
        Exam exam = new Exam(student.getId(), school.getId(), date, room, subject);
        int result = examService.addExam(exam);
        if (result > 0) {
            System.out.println("Lịch thi đã được thêm thành công!");
        } else {
            System.out.println("Không thể thêm lịch thi. Vui lòng thử lại.");
        }
        examList = examService.getAllExam();
        printTableExam(examList);
        askContinue();
    }

    private static Student inputAndCheckStudent() {
        long studentId;
        Student student;
        do {
            System.out.print("Nhập mã hs: ");
            studentId = scanner.nextLong();
            scanner.nextLine();
            student = studentService.getStudentById(studentId);
            if (student == null) {
                System.out.println("Học sinh không tồn tại, Vui lòng nhập lại");
            } else {
                Table.render(new Student[]{student}, Student.class).run();
            }
        } while (student == null);
        return student;
    }

    private static School inputAndCheckSchool() {
        long schoolId;
        School school;
        do {
            System.out.print("Nhập mã trường thi: ");
            schoolId = scanner.nextLong();
            scanner.nextLine();
            school = schoolService.getSchoolById(schoolId);
            if (school == null) {
                System.out.println("Trường không tồn tại, Vui lòng nhập lại");
            } else {
                Table.render(new School[]{school}, School.class).run();
            }
        } while (school == null);

        return school;
    }

    private static Date inputAndCheckDueDate() {
        String dueDate;
        Date date = null;
        do {
            System.out.print("Nhập ngày thi (yyyy-MM-dd): ");
            dueDate = scanner.nextLine();
            try {
                date = formatter.parse(dueDate);
            } catch (ParseException e) {
                System.out.println(
                    "Ngày thi không hợp lệ, định dạng hợp lệ (yyyy-MM-dd). Vui lòng nhập lại");
            }
        } while (date == null);

        return date;
    }

    private static String inputRoom() {
        String room;
        do {
            System.out.print("Nhp phòng thi: ");
            room = scanner.nextLine().trim();
        } while (room.equals(""));

        return room;
    }

    private static String inputSubject() {
        String subject;
        do {
            System.out.print("Nhập môn học: ");
            subject = scanner.nextLine().trim();
        } while (subject.equals(""));

        return subject;
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