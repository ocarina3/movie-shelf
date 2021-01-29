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

    public ArrayList<Movie> readAllMovies() {
        return repositoryMovie.readAllMovies();
    }

    public Movie readMoviesById(String value) {
        return repositoryMovie.readMoviesById(value);
    }

    public ArrayList<Movie> readMoviesByName(String value) {
        return repositoryMovie.readMoviesByName(value);
    }

    public ArrayList<Movie> searchMovies(String value) {
        return repositoryMovie.searchMovie(value);
    }

    public void updateMovie(Movie movie) {
        if(repositoryMovie.readMoviesById(String.format("%d", movie.getId())) != null) {
            repositoryMovie.updateMovie(movie);
        }
    }

    public void deleteMovieByName(Movie movie) {
        if(repositoryMovie.readMoviesByName(movie.getName()) != null) {
            repositoryMovie.deleteMovieByName(movie.getName());
        }
    }

}
