package com.dgwon.sterncourserecommendations.domain;

import javax.persistence.*;

@Entity
public class Rating {
    @EmbeddedId
    private RatingPk pk;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 2000)
    private String comment;

    @Column
    private Boolean recommended;

    public Rating(RatingPk pk, Integer rating, String comment, Boolean recommended) {
        this.pk = pk;
        this.rating = rating;
        this.comment = comment;
        this.recommended = recommended;
    }

    protected Rating() {}

    public RatingPk getPk() { return pk; }

    public void setPk(RatingPk pk) { this.pk = pk; }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
