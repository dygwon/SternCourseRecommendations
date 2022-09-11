package com.dgwon.sterncourserecommendations.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Course {
    @Id
    private String code;

    @Column
    private String title;

    @Column
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

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(code, course.code) && Objects.equals(title, course.title) && department == course.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, title, department);
    }
}
