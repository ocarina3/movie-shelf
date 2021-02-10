package model;

import model.entity.Movie;
import model.entity.Rating;
import model.entity.User;
import model.repository.RepositoryRating;

import java.util.ArrayList;

public class ModelRating {

    /*
     * Aqui chamamos as funções do RepositoryRating e colocamos quais as condições para
     * chamarmos ela.
     *
     * Por isso, utilizaremos somente o Model para chamarmos essas funções no resto do programa.
     * */

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

    //__________________________________________CREATE_________________________________________________________________

    //Caso não exista a avaliação, utilizar o createRating
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

    //Cria um rating e manda para o createRating
    public boolean createRating(float rate, int id_user, int id_movie){
        Rating rating = new Rating(rate,id_user,id_movie);
        return createRating(rating);
    }

    //__________________________________________READ_________________________________________________________________

    //Le a avaliação pelo id
    public Rating readRatingById(int rating_id) {
        return repositoryRating.readRatingById(rating_id);
    }

    //Le todas as avaliações
    public ArrayList<Rating> readAllRatings() {
        return repositoryRating.readAllRatings();
    }

    //Le todas as avaliações que tem uma nota igual a selecionada
    public ArrayList<Rating> readRatingsByValue(String value) {
        return repositoryRating.readRatingsByValue(value);
    }

    //Le avaliações que um filme recebeu
    public ArrayList<Rating> readRatingsByMovie(Movie movie) {
        return repositoryRating.readRatingsByMovie(movie);
    }

    //Le as avaliações que um usuario fez
    public ArrayList<Rating> readRatingsByUser(User user) {
        return repositoryRating.readRatingsByUser(user);
    }

    //Le o nome do usuario que fez a a avaliação
    public String readRaterUserName(int rating_id) {
        return repositoryRating.readRaterUserName(rating_id);
    }

    //Le uma avaliaçao que o usuario fez para um filme
    public Rating readRatingByUserAndMovie(Movie movie, User user) {
        if (
                repositoryRating.readRatingByUserAndMovie(movie, user) == null ||
                repositoryRating.readRatingByUserAndMovie(movie, user).size() == 0
        ) return null;
        else return repositoryRating.readRatingByUserAndMovie(movie, user).get(0);
    }

    //Le a nota media de um filme
    public float readAvgRatingByMovie(Movie movie)  {
        return repositoryRating.readAvgRatingByMovie(movie);
    }

    //Le os emails que ja avaliaram o filme
    public ArrayList<String> readAlreadyRatedEmails(Movie movie) {
        return repositoryRating.readAlreadyRatedEmails(movie);
    }

    //__________________________________________UPDATE_________________________________________________________________

    //Caso exista a avaliação, utiliza o updateRatingValueById
    public void updateRatingValueById(float newRating, int id ) {
        if(repositoryRating.readRatingById(id) != null)
            repositoryRating.updateRatingValueById(newRating, id );
    }

    //__________________________________________DELETE_________________________________________________________________

    //Caso exista a avaliação, utiliza o deleteRatingById
    public void deleteRatingById(int rating_id) {
        if(repositoryRating.readRatingById(rating_id) != null)
            repositoryRating.deleteRatingById(rating_id);
    }
}
