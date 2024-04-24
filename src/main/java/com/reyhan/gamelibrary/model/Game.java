package com.reyhan.gamelibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Pattern;


@Getter
@Setter
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    @Pattern(regexp = "^(19[5-9][0-9]|20[0-1][0-9]|202[0-4])$", message = "Year must be between 1950 and 2024 and in the format YYYY")
    private String year;

    @Column(name = "rating")
    @Pattern(regexp = "^[0-9]\\.[0-9]{2}$", message = "Rating must be a number between 0.00 and 9.99")
    private String rating;

    public Game() {
    }

    public Game(String title, String year, String rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

}
