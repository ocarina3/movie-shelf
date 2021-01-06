package ClassesBase;

import java.util.Objects;

public class Movie {

    //ATRIBUTOS
    private int id;
    private String name;
    private float score;
    private String movieDirector;
    private Genre movieGenre;
    private String synopsis;
    private int minimumAge;
    //_______________________________________________________________________________________________________________

    //CONSTRUCTOR
    public Movie(int id, String name, float score, String movieDirector, Genre movieGenre, String synopsis, int minimumAge) {
        this.id = id;
        this.name = name;
        this.score = score;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
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
        return id == movie.id && Float.compare(movie.score, score) == 0 && minimumAge == movie.minimumAge &&
                Objects.equals(name, movie.name) && Objects.equals(movieDirector, movie.movieDirector) &&
                movieGenre == movie.movieGenre && Objects.equals(synopsis, movie.synopsis);
    }
    //_______________________________________________________________________________________________________________
}
