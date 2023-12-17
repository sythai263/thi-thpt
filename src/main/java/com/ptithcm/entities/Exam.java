package com.ptithcm.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exam {

    Long id;
    Long studentId;
    String studentName;
    Long schoolId;
    String schoolName;

    Date dueDate;
    String room;
    String subject;
    Boolean existing;

    public Exam(Long studentId, Long schoolId, Date dueDate, String room, String subject) {
        this.studentId = studentId;
        this.schoolId = schoolId;
        this.dueDate = dueDate;
        this.room = room;
        this.subject = subject;
    }

    public Exam() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getExisting() {
        return existing;
    }

    public void setExisting(Boolean existing) {
        this.existing = existing;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDueDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(dueDate);


    }

    public String toCsvRow() {
        return String.format("%s, %s, %s, %s, %s", getSchoolName(), getRoom(),
            getDueDateFormat(), getStudentName(), getSubject());
    }
}
