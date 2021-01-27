package view;

import data.base.Connect;
import data.base.CreateDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelMovie;
import model.ModelRating;
import model.ModelUser;
import model.entity.Genre;
import model.entity.Movie;
import model.entity.Rating;
import model.entity.User;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        // Instancia responsável por criar a base de dados
        CreateDatabase createDatabase = new CreateDatabase();
        createDatabase.createTableUser();
        createDatabase.createTableMovie();
        createDatabase.createTableRating();


//_________________________________________USER____________________________________________________________

        User user1 = new User(0,"arthur","arthur.eu@edu.br","senha",LocalDate.of(2001,01,29));


        //CRIA UM NOVO USER
        ModelUser.getInstance().createClient(user1);

        //TENTAR CRIAR UM USER REPETIDO
        ModelUser.getInstance().createClient(user1);


        //LEITURA DO USER
        user1 = ModelUser.getInstance().readUsersByEmail(user1.getEmail());
        System.out.println(ModelUser.getInstance().readUsersByEmail(user1.getEmail()));

        //UPLOAD DO USER
        user1.setPassword("secreta");
        ModelUser.getInstance().updateUser(user1);
        System.out.println(ModelUser.getInstance().readUsersById(user1.getId()));

        //DELETANDO USER
        ModelUser.getInstance().deleteUserByEmail("arthur.eu@edu.br");

//___________________________________________MOVIE_____________________________________________________________


        //CRIAR MOVIE
        Movie movie = new Movie(0,"filme01","exemplo", Genre.COMEDY,"um filme qualquer",12);
        ModelMovie.getInstance().createMovie(movie);

        //LEITURA DO MOVIE
        movie = ModelMovie.getInstance().readMoviesByName(movie.getName()).get(0);
        System.out.println(movie);

        //UPLOAD DO MOVIE
        movie.setSynopsis("Um exemplo de filme");
        ModelMovie.getInstance().updateMovie(movie);

        //DELETANDO O MOVIE
        System.out.println(ModelMovie.getInstance().readMoviesById(String.format("%d",movie.getId())));

//_____________________________________________________________________________________________________________________


        ModelMovie.getInstance().deleteMovieByName(movie);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
