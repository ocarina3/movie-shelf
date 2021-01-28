package view.principal;

import data.base.Connect;
import data.base.CreateDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    private static Stage stage;
    private static Scene mainScene;
    private static Scene registerScene;
    private static Scene loginScene;
    private static Scene homeScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("main.fxml"));
        mainScene = new Scene (fxmlMain);

        Parent fxmlRegister = FXMLLoader.load(getClass().getResource("../register/register.fxml"));
        registerScene = new Scene (fxmlRegister);

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
        loginScene = new Scene (fxmlLogin);

        Parent fxmlHome = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        homeScene = new Scene(fxmlHome);

        primaryStage.setTitle("Ocarina");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../resources/icone.png")));

        primaryStage.setScene(mainScene);
        primaryStage.show();

        // Instancia responsável por criar a base de dados
        CreateDatabase createDatabase = new CreateDatabase();
        createDatabase.createTableUser();
        createDatabase.createTableMovie();
        createDatabase.createTableRating();

        Movie movie1 = new Movie(1, "Filme", "Diretor", Genre.DRAMA, "Sinopse", 10);
        Movie movie2 = new Movie(2, "Filme2", "Diretor2", Genre.COMEDY, "Sinopse2", 10);
        User user = new User(1, "Usuario", "usuario@email.com", "senha", LocalDate.of(2002, 1, 1));
        User user2 = new User(2, "Usuario2", "usuario2@email.com", "senha", LocalDate.of(2002, 1, 1));
       // Rating rating = new Rating(1, 4, 1, 1);
       // Rating rating2 = new Rating(2, 4, 2, 1);
       // Rating rating3 = new Rating(3, 4, 1, 2);

        ModelMovie.getInstance().createMovie(movie1);
        ModelMovie.getInstance().createMovie(movie2);
        ModelUser.getInstance().createClient(user);
        ModelUser.getInstance().createClient(user2);
       // ModelRating.getInstance().createRating(rating);
       // ModelRating.getInstance().createRating(rating2);
       // ModelRating.getInstance().createRating(rating3);

        System.out.println(ModelRating.getInstance().readAlreadyRatedEmails(1));

        ModelMovie.getInstance().deleteMovieByName(movie1);
        ModelMovie.getInstance().deleteMovieByName(movie2);
        ModelUser.getInstance().deleteUserByEmail(user.getEmail());
        ModelUser.getInstance().deleteUserByEmail(user2.getEmail());
        //ModelRating.getInstance().deleteRatingById(rating.getId());
       // ModelRating.getInstance().deleteRatingById(rating2.getId());
       // ModelRating.getInstance().deleteRatingById(rating3.getId());


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

    public static void changeScreen (String scr) {
        switch(scr){
            case "main":
                stage.setScene(mainScene);
                break;
            case "register":
                stage.setScene(registerScene);
                break;
            case "login":
                stage.setScene(loginScene);
                break;
            case "home":
                stage.setScene(homeScene);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
