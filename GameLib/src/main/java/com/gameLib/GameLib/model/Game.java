package com.gameLib.GameLib.model;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @Id
    private String id;

    private String name;
    private String description;
    private String releaseDate;
    private double evaluation;

    public Game() {}

    public Game(String name, String description, String releaseDate, double evaluation) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.evaluation = evaluation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }
}
