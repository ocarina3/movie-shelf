package model;

import model.entity.Movie;
import model.repository.RepositoryMovie;

public class ModelMovie {

    private RepositoryMovie repositoryMovie;

    private static ModelMovie instance;

    public static synchronized ModelMovie getInstance() {
        if(instance == null){
            instance = new ModelMovie();
        }
        return instance;
    }

    private ModelMovie() {
        repositoryMovie = new RepositoryMovie();
    }

    public void createMovie(Movie movie) {
        if(repositoryMovie.readMoviesByName(movie.getName()) != null) {
            repositoryMovie.createMovie(movie);
        }
    }

}
