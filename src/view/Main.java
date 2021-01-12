package view;

import data.base.Connect;
import data.base.CreateDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        // Testing database connection
        Connect connect = new Connect();

        // Instancia responsável por criar a base de dadados
        CreateDatabase createDatabase = new CreateDatabase(connect);
        createDatabase.createTableUser();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
