package com.ptithcm;

import com.ptithcm.common.Menu;
import com.ptithcm.entities.Exam;
import com.ptithcm.entities.School;
import com.ptithcm.entities.Student;
import com.ptithcm.services.ExamService;
import com.ptithcm.services.SchoolService;
import com.ptithcm.services.StudentService;
import pl.mjaron.etudes.Table;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
                case 8 -> getStudentByGroupSubject();

            }
            clear();
        }
        System.out.println("Bye bye");
        scanner.close();
    }

    public static Student insertStudent() {
        String name = inputCheckEmpty("tên");
        String studentClass = inputCheckEmpty("lớp");
        School school = inputAndCheckSchool();
        String priority = inputStudentPriority();
        String groupSubject = inputAndCheckGroupSubject();

        return new Student(name, studentClass, school.getId(), groupSubject, Integer.parseInt(priority));
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
        int rowsInserted = studentService.addStudent(insertStudent());
        if (rowsInserted > 0) {
            System.out.println("Sinh viên đã được thêm thành công!");
        } else {
            System.out.println("Không thể thêm sinh viên. Vui lòng thử lại.");
        }
        askContinue();
    }

    public static void updateStudent() throws IOException {
        Student student = inputAndCheckStudent();
        System.out.println("Chỉnh sửa thông tin học sinh");
        Student studentUpdate = insertStudent();
        studentUpdate.setId(student.getId());
        int rowsUpdated = studentService.updateStudent(studentUpdate);
        if (rowsUpdated > 0) {
            System.out.println("Cập nhật sinh viên thành công!");
        } else {
            System.out.println("Cập nhật sinh viên thất bại. Vui lòng thử lại.");
        }
        System.out.println("Danh sách học sinh sau khi cập nhật!");
        List<Student> list = studentService.getStudents();
        Table.render(list, Student.class).run();
        askContinue();
    }

    public static void deleteStudent() throws IOException {
        Student student = inputAndCheckStudent();
        int rowsUpdated = studentService.deleteStudent(student);
        if (rowsUpdated > 0) {
            System.out.println("Xoá sinh viên thành công!");
        } else {
            System.out.println("Xoá sinh viên thất bại. Vui lòng thử lại.");
        }
        System.out.println("Danh sách học sinh sau khi xoá!");
        List<Student> list = studentService.getStudents();
        Table.render(list, Student.class).run();
        askContinue();
    }

    public static void getStudentByName() throws IOException {
        System.out.print("Nhập tên hoặc mã học sinh cần tìm: ");
        String input = scanner.nextLine();

        try {
            Long studentId = Long.parseLong(input);
            Student student = studentService.getStudentById(studentId);

            if (student == null) {
                System.out.println("Không tìm thấy học sinh có mã: " + studentId);
            } else {
                Table.render(new Student[]{student}, Student.class).run();
            }
            askContinue();
        } catch (NumberFormatException e) {
            List<Student> students = studentService.getStudentByName(input);
            if (students.isEmpty()) {
                System.out.println("Không tìm thấy học sinh có tên: " + input);
                askContinue();
            } else {
                printTableStudent(students);
            }
        }
    }

    public static void getStudentByGroupSubject() throws IOException {
        String groupSubject = inputAndCheckGroupSubject();
        List<Student> students = studentService.getStudentByGroupSubject(groupSubject);
        printTableStudent(students);
    }

    public static void addSchool() throws IOException {
        String name = inputCheckEmpty("tên trường");
        String address = inputCheckEmpty("địa chỉ");

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
        String room = inputCheckEmpty("phòng thi");
        String subject = inputCheckEmpty("môn học");
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
            System.out.print("Nhập mã học sinh: ");
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
            System.out.print("Nhập mã trường: ");
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

    private static String inputCheckEmpty(String value) {
        String input;
        do {
            System.out.print("Nhập " + value + ": ");
            input = scanner.nextLine().trim();
        } while (input.equals(""));
        return input;
    }

    private static String inputAndCheckGroupSubject() {
        String groupSubject;
        do {
            System.out.print("Nhập khối (A, B hoặc C): ");
            groupSubject = scanner.nextLine().trim();
            if ("A".equals(groupSubject) || "B".equals(groupSubject) || "C".equals(groupSubject)) {
                break;
            } else {
                System.out.println("Khối không hợp lệ. Vui lòng nhập lại.");
            }
        } while (true);
        return groupSubject;
    }

    private static String inputStudentPriority() {
        String studentPriority;
        do {
            System.out.print("Nhập loại ưu tiên: ");
            studentPriority = scanner.nextLine().trim();

            if (studentPriority.isEmpty() || !isNumeric(studentPriority)) {
                System.out.println("Loại ưu tiên không hợp lệ. Vui lòng nhập lại.");
            }
        } while (studentPriority.isEmpty() || !isNumeric(studentPriority));

        return studentPriority;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
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