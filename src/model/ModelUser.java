package model;

import model.entity.User;
import model.repository.RepositoryUser;

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

    public void createClient(User user) {
        if(repositoryUser.readUsersByEmail(user.getEmail()) != null){
            repositoryUser.createClient(user);
        }
    }

    public void createAdmin(User user) {
        if(repositoryUser.readUsersByEmail(user.getEmail()) != null){
            repositoryUser.createAdmin(user);
        }
    }
}
