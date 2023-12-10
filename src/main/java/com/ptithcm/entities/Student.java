package com.ptithcm.entities;

import com.google.gson.Gson;

public class Student {

  protected Long id;
  protected String name;
  protected String studentClass;
  protected Long schoolId;
  protected String schoolName;
  protected String groupSubject;
  protected Integer priority;
  protected Boolean isExit;

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

  public String getStudentClass() {
    return studentClass;
  }

  public void setStudentClass(String studentClass) {
    this.studentClass = studentClass;
  }

  public Long getSchoolId() {
    return schoolId;
  }

  public void setSchoolId(Long schoolId) {
    this.schoolId = schoolId;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  public String getGroupSubject() {
    return groupSubject;
  }

  public void setGroupSubject(String groupSubject) {
    this.groupSubject = groupSubject;
  }

  public Boolean getExit() {
    return isExit;
  }

  public void setExit(Boolean exit) {
    isExit = exit;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public String toJsonString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }

}
