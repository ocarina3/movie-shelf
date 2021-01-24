package model;

import model.entity.Rating;
import model.repository.RepositoryRating;

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

    public void createRating(Rating rating) {
        repositoryRating.createRating(rating);
    }
}
