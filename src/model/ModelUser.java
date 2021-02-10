package model;

import model.entity.Movie;
import model.entity.Rating;
import model.entity.User;
import model.repository.RepositoryUser;

import java.util.ArrayList;

public class ModelUser {

    /*
     * Aqui chamamos as funções do RepositoryUser e colocamos quais as condições para
     * chamarmos ela.
     *
     * Por isso, utilizaremos somente o Model para chamarmos essas funções no resto do programa.
     * */

    private RepositoryUser repositoryUser;
    private static ModelUser instance;

    public static synchronized ModelUser getInstance(){
        if(instance == null){
            instance = new ModelUser();
        }
        return instance;
    }

    private ModelUser(){
        repositoryUser = new RepositoryUser();
    }

    //__________________________________________CREATE_________________________________________________________________

    //Caso não exista um usuario com o mesmo email, utiliza o createClient
    public boolean createClient(User user) {
        if(repositoryUser.readUsersByEmail(user.getEmail()) == null){
            repositoryUser.createClient(user);
            return true;
        }else{return false;}
    }

    //caso o filme não esteja na lista de favoritos do usuario, utiliza o favoriteMovies
    public void favoriteMovies(User user , Movie movie){
        if (!(repositoryUser.isFavotited(user, movie))) { repositoryUser.favoriteMovies(user,movie);}
    }

    //__________________________________________READ_________________________________________________________________

    //Le o usuario pelo id
    public User readUsersById(int value){
        return repositoryUser.readUsersById(String.format("%d",value));
    }

    //Le o usuario pelo email
    public User readUsersByEmail(String value) {
        return repositoryUser.readUsersByEmail(value);
    }

    //Le os usuarios com o mesmo nome
    public ArrayList<User> readUsersByName(String value) {
        return repositoryUser.readUsersByName(value);
    }

    //Le os filmes favorito
    public ArrayList <Movie> readFavoriteMovies(User user){return repositoryUser.readFavoriteMovies(user);}

    //Verifica se um filme esta na lista de favoritos do usuario
    public boolean isFavotited(User user, Movie movie) {
        if(readUsersById(user.getId()) != null){
            return repositoryUser.isFavotited(user, movie);
        }
        else return false;
    }

    //Verifica se o usuario é um admin
    public boolean isAdmin(User user){
        if(ModelUser.getInstance().readUsersById(user.getId()) != null){
            return repositoryUser.isAdmin(user);
        }
        else return false;
    }

    //__________________________________________UPDATE_________________________________________________________________

    //Caso o usuario exista, utiliza o updateUser
    public boolean updateUser(User user) {
        if(readUsersById(user.getId()) != null &&
        (readUsersByEmail(user.getEmail()) == null|| readUsersByEmail(user.getEmail()).getEmail().equals(user.getEmail())) ){
            repositoryUser.updateUser(user);
            return true;
        } else {
            return false;
        }
    }
    
    //_______________________________________________________________________________________________________________
    //DELETE

    /*
    *Caso o usuario exista, deleta o usuario e todas as suas avaliações
    */


    //deleta o usuario peli email
    public void deleteUserByEmail(String value){
        if(repositoryUser.readUsersByEmail(value) != null) {
            if (ModelRating.getInstance().readRatingsByUser(repositoryUser.readUsersByEmail(value)).size() != 0) {
                ArrayList<Rating> userRatings = ModelRating.getInstance().readRatingsByUser(repositoryUser.readUsersByEmail(value));
                for( Rating userRating : userRatings ) {
                    ModelRating.getInstance().deleteRatingById(userRating.getId());
                }
            }

            repositoryUser.deleteUserByEmail(value);
        }
    }

    //deleta o usuario pelo Id
    public void deleteUserById(int value){
        if (repositoryUser.readUsersById(String.format("%d",value)) != null) {
            if (ModelRating.getInstance().readRatingsByUser(repositoryUser.readUsersById(Integer.toString(value))).size() != 0) {
                ArrayList<Rating> userRatings = ModelRating.getInstance().readRatingsByUser(repositoryUser.readUsersById(Integer.toString(value)));
                for( Rating userRating : userRatings ) {
                    ModelRating.getInstance().deleteRatingById(userRating.getId());
                }
            }

            repositoryUser.deleteUserById(String.format("%d", value));
        }
    }

    //Caso o filme esteja favoritado pelo usuario, utiliza o deleteFavoriteMovies
    public void deleteFavoriteMovies(User user, Movie movie){
        if (repositoryUser.isFavotited(user,movie)){repositoryUser.deleteFavoriteMovies(user,movie);}
    }
    
}
