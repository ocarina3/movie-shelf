package model;

public class Rating {
    private int id;
    private float rating;
    private int userId;
    private int movieId;

    public Rating(int id, float rating, User user, Movie movie) {
        this.id = id;
        this.rating = rating;
        userId = user.getId();
        movieId = movie.getId();
    }
}
