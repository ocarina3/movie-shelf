package model;

import model.entity.Movie;
import model.entity.Rating;
import model.repository.RepositoryMovie;

import java.util.ArrayList;

public class ModelMovie {


    /*
    * Aqui chamamos as funções do RepositoryMovie e colocamos quais as condições para
    * chamarmos ela.
    *
    * Por isso, utilizaremos somente o Model para chamarmos essas funções no resto do programa.
    * */

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

    //__________________________________________CREATE_________________________________________________________________

    //Verifica se o filme ja existe, se não, utiliza o createMovie
    public boolean createMovie(Movie movie) {
        if(repositoryMovie.readMoviesByName(movie.getName()) != null) {
            repositoryMovie.createMovie(movie);
            return true;
        } else {
            return false;
        }
    }

    //__________________________________________READ_________________________________________________________________


    //Le todos os filmes
    public ArrayList<Movie> readAllMovies() {
        return repositoryMovie.readAllMovies();
    }

    //seleciona o atributo id para o readMovies
    public Movie readMoviesById(String value) {
        return repositoryMovie.readMoviesById(value);
    }

    //seleciona o atributo nome para o readMovies
    public ArrayList<Movie> readMoviesByName(String value) {
        return repositoryMovie.readMoviesByName(value);
    }

    //busca filmes que tenham um nome Similar ao valor escolhido
    public ArrayList<Movie> searchMovies(String value) {
        return repositoryMovie.searchMovie(value);
    }

    //__________________________________________UPDATE_________________________________________________________________

    //Verifica se o filme existe, se sim, utiliza o UpdateMovie
    public boolean updateMovie(Movie movie) {
        if(repositoryMovie.readMoviesById(String.format("%d", movie.getId())) != null) {
            repositoryMovie.updateMovie(movie);
            return true;
        } else {
            return false;
        }
    }

    //__________________________________________DELETE_________________________________________________________________

    //Verifica se o filme existe, se sim, utiliza o DeleteMovieById
    public boolean deleteMovieById(String value) {
        if(repositoryMovie.readMoviesById(value) != null) {
            if (ModelRating.getInstance().readRatingsByMovie(repositoryMovie.readMoviesById(value)).size() != 0) {
                ArrayList<Rating> movieRatings = ModelRating.getInstance().readRatingsByMovie(repositoryMovie.readMoviesById(value));
                for( Rating movieRating : movieRatings ) {
                    ModelRating.getInstance().deleteRatingById(movieRating.getId());
                }
            }
            repositoryMovie.deleteMovieById(value);
            return true;
        } else {
            return false;
        }
    }

    //Verifica se o filme existe, se sim, utiliza o DeleteMovieByName
    public boolean deleteMovieByName(Movie movie) {
        if(repositoryMovie.readMoviesByName(movie.getName()) != null) {
            repositoryMovie.deleteMovieByName(movie.getName());
            return true;
        } else {
            return false;
        }
    }

}
