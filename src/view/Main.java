package view;

import data.base.Connect;
import data.base.CreateDatabase;
import data.base.DeleteDatabase;
import data.base.ReadDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.entity.User;
import utils.GenerateQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        // Testing database connection
        Connect connect = new Connect();
        connect.connect();
        connect.disconnect();


        // Instancia responsável por criar a base de dadados
        CreateDatabase createDatabase = new CreateDatabase();
        createDatabase.createTableUser();
        createDatabase.createTableMovie();
        createDatabase.createTableRating();



        DeleteDatabase deleteDatabase = new DeleteDatabase();

        // Teste
        User user1 = new User(12, "Noronha", "exemplo@exemplo.com.br", "123456", null);
        User user2 = new User(15, "Pessoa", "exemplo2@exemplo.com.br", "123456", null);

        createDatabase.createClient(user1);
        createDatabase.createClient(user2);
        ReadDatabase readDatabase = new ReadDatabase();
       User user3 = readDatabase.readUsersById("1");
       System.out.println(user3);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
