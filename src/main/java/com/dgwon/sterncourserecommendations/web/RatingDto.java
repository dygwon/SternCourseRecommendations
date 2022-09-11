package com.dgwon.sterncourserecommendations.web;

import com.dgwon.sterncourserecommendations.domain.Rating;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RatingDto {
    @Min(1)
    @Max(5)
    private Integer rating;

    @Size(max = 1024)
    private String comment;

    private Boolean recommended;

    @NotNull
    private String userId;

    public RatingDto(Rating rating) {
        this(rating.getRating(), rating.getComment(), rating.isRecommended(), rating.getPk().getUserId());
    }

    public RatingDto(Integer rating, String comment, Boolean recommended, String userId) {
        this.rating = rating;
        this.comment = comment;
        this.recommended = recommended;
        this.userId = userId;
    }

    protected RatingDto() {}

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
