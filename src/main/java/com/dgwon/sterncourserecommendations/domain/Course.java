package com.dgwon.sterncourserecommendations.domain;

import javax.persistence.*;

@Entity
public class Course {
    @Id
    private String courseCode;

    @Column
    private String title;

    @Column
    @Enumerated
    private Department department;

    public Course(String courseCode, String title, Department department) {
        this.courseCode = courseCode;
        this.title = title;
        this.department = department;
    }

    protected Course() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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
