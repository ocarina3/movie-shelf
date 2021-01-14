package model.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class Movie {

    private final DecimalFormat f = new DecimalFormat("#.#");

    //ATRIBUTOS
    private int id;
    private String name;
    private Double score;
    private String movieDirector;
    private Genre movieGenre;
    private String synopsis;
    private int minimumAge;
    private ArrayList<Rating> ratings;
    //_______________________________________________________________________________________________________________

    //CONSTRUCTOR
    public Movie(int id, String name, Double score, String movieDirector, Genre movieGenre, String synopsis, int minimumAge) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        this.id = id;
        this.name = name;
        this.score = Double.valueOf((f.format(score)).replace(",","."));
        this.movieDirector = movieDirector;
        this.movieGenre = movieGenre;
        this.synopsis = synopsis;
        this.minimumAge = minimumAge;
        ratings = new ArrayList<>();
    }
    //_______________________________________________________________________________________________________________

    //GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return Double.valueOf((f.format(score)).replace(",","."));
    }

    public void setScore(Double score) {
        this.score = Double.valueOf((f.format(score)).replace(",","."));
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public Genre getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(Genre movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }
    //_______________________________________________________________________________________________________________

    //EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return id == movie.id && Double.compare(movie.score, score) == 0 && minimumAge == movie.minimumAge &&
                Objects.equals(name, movie.name) && Objects.equals(movieDirector, movie.movieDirector) &&
                movieGenre == movie.movieGenre && Objects.equals(synopsis, movie.synopsis);
    }
    //_______________________________________________________________________________________________________________
}
