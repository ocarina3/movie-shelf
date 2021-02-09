package model.entity;

public class Rating {

    //ATRIBUTOS
    private int id;
    private float rating;
    private int userId;
    private int movieId;

    //CONSTRUCTOR
    public Rating(int id, float rating, int id_user, int id_movie) {
        this.id = id;
        this.rating = rating;
        userId = id_user;
        movieId = id_movie;
    }

    public Rating(float rating, int id_user, int id_movie) {
        this.rating = rating;
        userId = id_user;
        movieId = id_movie;
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

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rating=" + rating +
                ", userId=" + userId +
                ", movieId=" + movieId +
                '}';
    }
}
