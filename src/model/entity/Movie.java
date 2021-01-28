package model.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class Movie {

    private final DecimalFormat f = new DecimalFormat("#.#");

    //ATRIBUTOS
    private int id;
    private String name;
    private String movieDirector;
    private Genre movieGenre;
    private String synopsis;
    private int minimumAge;
    //_______________________________________________________________________________________________________________

    //CONSTRUCTOR
    public Movie() {
    }

    public Movie(String name, String movieDirector, Genre movieGenre, String synopsis, int minimumAge) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        this.id = id;
        this.name = name;
        this.movieDirector = movieDirector;
        this.movieGenre = movieGenre;
        this.synopsis = synopsis;
        this.minimumAge = minimumAge;
    }

    public Movie(int id, String name, String movieDirector, Genre movieGenre, String synopsis, int minimumAge) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        this.id = id;
        this.name = name;
        this.movieDirector = movieDirector;
        this.movieGenre = movieGenre;
        this.synopsis = synopsis;
        this.minimumAge = minimumAge;
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
        return id == movie.id && minimumAge == movie.minimumAge &&
                Objects.equals(name, movie.name) && Objects.equals(movieDirector, movie.movieDirector) &&
                movieGenre == movie.movieGenre && Objects.equals(synopsis, movie.synopsis);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movieDirector='" + movieDirector + '\'' +
                ", movieGenre=" + movieGenre.getDescription() +
                ", synopsis='" + synopsis + '\'' +
                ", minimumAge=" + minimumAge +
                '}';
    }

}
