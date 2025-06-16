package com.gameLib.GameLib.dto;

public class GameAverageRatingDTO {
    private String gameName;
    private double averageRating;

    public GameAverageRatingDTO(String gameName, double averageRating) {
        this.gameName = gameName;
        this.averageRating = averageRating;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
