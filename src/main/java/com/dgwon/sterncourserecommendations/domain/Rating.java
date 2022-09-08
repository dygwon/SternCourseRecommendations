package com.dgwon.sterncourserecommendations.domain;

import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue
    private Integer ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Column
    private int rating;

    @Column
    private boolean recommended;

    @Column(length = 2000)
    private String comment;

    public Rating(Course course, int rating, boolean recommended, String comment) {
        this.course = course;
        this.rating = rating;
        this.recommended = recommended;
        this.comment = comment;
    }

    protected Rating() {}

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
