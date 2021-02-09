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
    public boolean createRating(Rating rating) {

        if( ModelUser.getInstance().readUsersById(rating.getUserId()) == null ) return false;
        String raterUserEmail = ModelUser.getInstance().readUsersById(rating.getUserId()).getEmail();

        Movie ratedMovie = ModelMovie.getInstance().readMoviesById(Integer.toString(rating.getMovieId()));

        ArrayList<String> alreadyRatedEmails = repositoryRating.readAlreadyRatedEmails(ratedMovie);

        if (alreadyRatedEmails.contains(raterUserEmail)) {
            return false;
        } else {
            repositoryRating.createRating(rating);
            return true;
        }
    }

    public boolean createRating(float rate, int id_user, int id_movie){
        Rating rating = new Rating(rate,id_user,id_movie);
        return createRating(rating);
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

    public Rating readRatingByUserAndMovie(Movie movie, User user) {
        if (repositoryRating.readRatingByUserAndMovie(movie, user) == null) return null;
        else return repositoryRating.readRatingByUserAndMovie(movie, user).get(0);
    }

    public float readAvgRatingByMovie(Movie movie)  {
        return repositoryRating.readAvgRatingByMovie(movie);
    }

    public ArrayList<String> readAlreadyRatedEmails(Movie movie) {
        return repositoryRating.readAlreadyRatedEmails(movie);
    }

    // Update
    public void updateRatingValueById(float newRating, int id ) {
        if(repositoryRating.readRatingById(id) != null)
            repositoryRating.updateRatingValueById(newRating, id );
    }

    // Delete
    public void deleteRatingById(int rating_id) {
        if(repositoryRating.readRatingById(rating_id) != null)
            repositoryRating.deleteRatingById(rating_id);
    }
}
