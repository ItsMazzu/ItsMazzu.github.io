package com.gameLib.GameLib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewDTO {
    @JsonProperty("gameName")
    private String gameName;

    @JsonProperty("rating")
    private double rating;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("createdAt")
    private String createdAt;

    public ReviewDTO() {}

    public ReviewDTO(String gameName, double rating, String comment, String createdAt) {

        this.gameName = gameName;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
