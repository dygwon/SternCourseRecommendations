package com.dgwon.sterncourserecommendations.domain;

import javax.persistence.*;

@Entity
public class Course {
    @Id
    private String code;

    @Column
    private String title;

    @Column
    @Enumerated
    private Department department;

    public Course(String code, String title, Department department) {
        this.code = code;
        this.title = title;
        this.department = department;
    }

    protected Course() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String courseCode) {
        this.code = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
