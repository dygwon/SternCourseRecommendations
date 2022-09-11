package com.dgwon.sterncourserecommendations.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class RatingPk implements Serializable {
    @ManyToOne
    private Course course;

    @Column(insertable = false, updatable = false, nullable = false)
    private String userId;

    public RatingPk() {}

    public RatingPk(Course course, String userId) {
        this.course = course;
        this.userId = userId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
