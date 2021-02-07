package model;

import model.entity.Movie;
import model.entity.Rating;
import model.entity.User;
import model.repository.RepositoryRating;

import java.util.ArrayList;

public class ModelRating {

    private RepositoryRating repositoryRating;

    private static ModelRating instance;

    public static synchronized ModelRating getInstance() {
        if(instance == null){
            instance = new ModelRating();
        }
        return instance;
    }

    private ModelRating() {
        repositoryRating = new RepositoryRating();
    }

    // Create
    public void createRating(Rating rating) {

        if( ModelUser.getInstance().readUsersById(rating.getUserId()) == null ) return;
        String raterUserEmail = ModelUser.getInstance().readUsersById(rating.getUserId()).getEmail();

        Movie ratedMovie = ModelMovie.getInstance().readMoviesById(Integer.toString(rating.getMovieId()));

        ArrayList<String> alreadyRatedEmails = repositoryRating.readAlreadyRatedEmails(ratedMovie);

        if( !alreadyRatedEmails.contains(raterUserEmail) )
            repositoryRating.createRating(rating);
    }

    public void createRating(float rate, int id_user, int id_movie){
        Rating rating = new Rating(0,rate,id_user,id_movie);
        createRating(rating);
    }

    // Read
    public Rating readRatingById(int rating_id) {
        return repositoryRating.readRatingById(rating_id);
    }

    public ArrayList<Rating> readAllRatings() {
        return repositoryRating.readAllRatings();
    }

    public ArrayList<Rating> readRatingsByValue(String value) {
        return repositoryRating.readRatingsByValue(value);
    }

    public ArrayList<Rating> readRatingsByMovie(Movie movie) {
        return repositoryRating.readRatingsByMovie(movie);
    }

    public ArrayList<Rating> readRatingsByUser(User user) {
        return repositoryRating.readRatingsByUser(user);
    }

    public String readRaterUserName(int rating_id) {
        return repositoryRating.readRaterUserName(rating_id);
    }

    public Rating readUserRatingByMovie(Movie movie, User user) {
        if (repositoryRating.readUserRatingsByMovie(movie, user) == null) return null;
        return repositoryRating.readUserRatingsByMovie(movie, user).get(0);
    }

    public float readAvgRatingByMovie(Movie movie)  {
        return repositoryRating.readAvgRatingByMovie(movie);
    }

    public ArrayList<String> readAlreadyRatedEmails(Movie movie) {
        return repositoryRating.readAlreadyRatedEmails(movie);
    }

    // Update
    public void updateRatingValueById(int rating_id, float newRating) {
        if(repositoryRating.readRatingById(rating_id) != null || (newRating <= 5 && newRating >= 0))
            repositoryRating.updateRatingValueById(rating_id, newRating);
    }

    // Delete
    public void deleteRatingById(int rating_id) {
        if(repositoryRating.readRatingById(rating_id) != null)
            repositoryRating.deleteRatingById(rating_id);
    }
}
