package model;

import model.entity.Movie;
import model.entity.User;
import model.repository.RepositoryUser;

import java.util.ArrayList;

public class ModelUser {

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

    //_______________________________________________________________________________________________________________
    //CREATE
    
    public boolean createClient(User user) {
        if(repositoryUser.readUsersByEmail(user.getEmail()) == null){
            repositoryUser.createClient(user);
            return true;
        }else{return false;}
    }

    public boolean createAdmin(User user) {
        if(repositoryUser.readUsersByEmail(user.getEmail()) == null){
            repositoryUser.createAdmin(user);
            return true;
        } else {
            return false;
        }
    }
    public void favoriteMovies(User user , Movie movie){
        if (!(repositoryUser.isFavotited(user, movie))) { repositoryUser.favoriteMovies(user,movie);}
    }
    //_______________________________________________________________________________________________________________
    //READ

    public User readUsersById(int value){
        return repositoryUser.readUsersById(String.format("%d",value));
    }

    public User readUsersByEmail(String value) {
        return repositoryUser.readUsersByEmail(value);
    }

    public ArrayList<User> readUsersByName(String value) {
        return repositoryUser.readUsersByName(value);
    }

    public ArrayList <Movie> readFavoriteMovies(User user){return repositoryUser.readFavoriteMovies(user);}

    public boolean isFavotited(User user, Movie movie) {
        if(ModelUser.getInstance().readUsersById(user.getId()) != null){
            return repositoryUser.isFavotited(user, movie);
        }
        else return false;
        }

    public boolean isAdmin(User user){
        if(ModelUser.getInstance().readUsersById(user.getId()) != null){
            return repositoryUser.isAdmin(user);
        }
        else return false;
    }
    //_______________________________________________________________________________________________________________
    //UPDATE
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

    public void deleteUserByEmail(String value){
        if(repositoryUser.readUsersByEmail(value) != null)
        repositoryUser.deleteUserByEmail(value);
    }

    public void deleteUserById(int value){
        if (repositoryUser.readUsersById(String.format("%d",value)) != null)
        repositoryUser.deleteUserById(String.format("%d",value));
    }

    public void deleteFavoriteMovies(User user, Movie movie){
        if (repositoryUser.isFavotited(user,movie)){repositoryUser.deleteFavoriteMovies(user,movie);}
    }
    
}
