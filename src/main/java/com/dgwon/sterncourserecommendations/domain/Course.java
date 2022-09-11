package com.dgwon.sterncourserecommendations.domain;

import javax.persistence.*;

@Entity
public class Course {
    @Id
    private String id;

    @Column
    private String title;

    @Column
    @Enumerated
    private Department department;

    public Course(String id, String title, Department department) {
        this.id = id;
        this.title = title;
        this.department = department;
    }

    protected Course() {
    }

    public String getId() {
        return id;
    }

    public void setId(String courseCode) {
        this.id = courseCode;
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
