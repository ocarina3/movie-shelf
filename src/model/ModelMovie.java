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

    public boolean createMovie(Movie movie) {
        if(repositoryMovie.readMoviesByName(movie.getName()) != null) {
            repositoryMovie.createMovie(movie);
            return true;
        } else {
            return false;
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

    public boolean updateMovie(Movie movie) {
        if(repositoryMovie.readMoviesById(String.format("%d", movie.getId())) != null) {
            repositoryMovie.updateMovie(movie);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteMovieById(String value) {
        if(repositoryMovie.readMoviesById(value) != null) {
            repositoryMovie.deleteMovieById(value);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteMovieByName(Movie movie) {
        if(repositoryMovie.readMoviesByName(movie.getName()) != null) {
            repositoryMovie.deleteMovieByName(movie.getName());
            return true;
        } else {
            return false;
        }
    }

}
