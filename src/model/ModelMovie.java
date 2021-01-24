package model;

import model.entity.Movie;
import model.repository.RepositoryMovie;

import java.util.ArrayList;

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

    public Movie readMoviesById(String value) {
        if(repositoryMovie.readMoviesById(value) != null) {
            return repositoryMovie.readMoviesById(value);
        } else {
            return null;
        }
    }

    public ArrayList<Movie> readMoviesByName(String value) {
        if(repositoryMovie.readMoviesByName(value) != null) {
            return repositoryMovie.readMoviesByName(value);
        } else {
            return null;
        }
    }

    public void deleteMovieByName(Movie movie) {
        if(repositoryMovie.readMoviesByName(movie.getName()) != null) {
            repositoryMovie.deleteMovieByName(movie.getName());
        }
    }

}
