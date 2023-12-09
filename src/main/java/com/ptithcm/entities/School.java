package com.ptithcm.entities;

import com.google.gson.Gson;

public class School {
    private Long schoolId;
    private String schoolName;
    private Boolean isExit;

    public School() {
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

    public Boolean getIsExit() {
        return isExit;
    }

    public void setIsExit(Boolean exit) {
        isExit = exit;
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
