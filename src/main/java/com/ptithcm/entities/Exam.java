package com.ptithcm.entities;

import java.util.Date;

public class Exam {

  Long id;
  Long studentId;
  Long schoolId;

  Date dueDate;
  String room;
  String subject;
  Boolean existing;

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
}
