package model;

public class Rating {

    //ATRIBUTOS
    private int id;
    private float rating;
    private int userId;
    private int movieId;

    //CONSTRUCTOR
    public Rating(int id, float rating, User user, Movie movie) {
        this.id = id;
        this.rating = rating;
        userId = user.getId();
        movieId = movie.getId();
    }
    //_______________________________________________________________________________________________________________

    //GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
