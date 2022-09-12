package com.dgwon.sterncourserecommendations.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingPk implements Serializable {
    @ManyToOne
    private Course course;

    @Column(insertable = false, updatable = false, nullable = false)
    private Integer studentId;

    public RatingPk() {}

    public RatingPk(Course course, Integer studentId) {
        this.course = course;
        this.studentId = studentId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "RatingPk{" +
                "course=" + course +
                ", studentId=" + studentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingPk ratingPk = (RatingPk) o;
        return Objects.equals(course, ratingPk.course) && Objects.equals(studentId, ratingPk.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, studentId);
    }
}
