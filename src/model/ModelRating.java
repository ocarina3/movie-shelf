package model;

import model.entity.Rating;
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
        String raterUserEmail = ModelUser.getInstance().readUsersById(rating.getUserId()).getEmail();

        ArrayList<String> alreadyRatedEmails = repositoryRating.readAlreadyRatedEmails(rating.getMovieId());

        if( !alreadyRatedEmails.contains(raterUserEmail) )
            repositoryRating.createRating(rating);
    }

    // Read
    public Rating readRatingById(int rating_id) {
        return repositoryRating.readRatingById(rating_id);
    }

    public ArrayList<Rating> readRatingsByValue(String value) {
        return repositoryRating.readRatingsByValue(value);
    }

    public String readRaterUserName(int rating_id) {
        return repositoryRating.readRaterUserName(rating_id);
    }

    public String readRatedMovieName(int rating_id) {
        return repositoryRating.readRatedMovieName(rating_id);
    }

    public ArrayList<String> readAlreadyRatedEmails(int movie_id) {
        return repositoryRating.readAlreadyRatedEmails(movie_id);
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
