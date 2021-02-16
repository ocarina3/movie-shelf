package view.principal;

import database.CreateDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class Main extends Application {
    public static Stage stage;
    private static Scene mainScene;
    private static Scene registerScene;
    private static Scene loginScene;
    private static Scene homeScene;
    private static Scene admScene;
    private static Scene listScene;
    private static Scene changeDataScene;
    private static Scene addMovies;
    private static Scene updateMovies;
    private static Scene deleteMovies;

    @Override
    public void start(Stage primaryStage) throws Exception{

        /**
         * Inicializa as Telas e guarda as informações pra quando forem iniciadas
         * */
        stage = primaryStage;

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("main.fxml"));
        mainScene = new Scene (fxmlMain);

        Parent fxmlRegister = FXMLLoader.load(getClass().getResource("../register/register.fxml"));
        registerScene = new Scene (fxmlRegister);

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
        loginScene = new Scene (fxmlLogin);

        Parent fxmlHome = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        homeScene = new Scene(fxmlHome);

        Parent fxmlAdm = FXMLLoader.load(getClass().getResource("../adm/adm.fxml"));
        admScene = new Scene(fxmlAdm);

        Parent fxmlChangeData = FXMLLoader.load(getClass().getResource("../changedata/changedata.fxml"));
        changeDataScene = new Scene(fxmlChangeData);

        Parent fxmlAddMovies = FXMLLoader.load(getClass().getResource("../add/add.fxml"));
        addMovies = new Scene(fxmlAddMovies);

        Parent fxmlUpdateMovies = FXMLLoader.load(getClass().getResource("../update/update.fxml"));
        updateMovies = new Scene(fxmlUpdateMovies);

        Parent fxmlDeleteMovies = FXMLLoader.load(getClass().getResource("../delete/delete.fxml"));
        deleteMovies = new Scene(fxmlDeleteMovies);

        /**
         * Inicia a tela inicial
         * */

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
        createDatabase.createTableFavoriteMovies();
    }

    /**
     * Muda a tela de acordo com a tela que foi selecionada
     */

    public static void changeScreen (String scr, String currentUser) {
        switch(scr){
            case "main":
                stage.setScene(mainScene);
                notifyAllListeners("main", currentUser);
                break;
            case "register":
                stage.setScene(registerScene);
                notifyAllListeners("register", currentUser);
                break;
            case "login":
                stage.setScene(loginScene);
                notifyAllListeners("login", currentUser);
                break;
            case "home":
                stage.setScene(homeScene);
                notifyAllListeners("home", currentUser);
                break;
            case "adm":
                stage.setScene(admScene);
                notifyAllListeners("adm", currentUser);
                break;
            case "list":
                Parent fxmlList = null;
                try {
                    fxmlList = FXMLLoader.load(Main.class.getResource("../list/list.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                listScene = new Scene(fxmlList);
                stage.setScene(listScene);
                notifyAllListeners("list", currentUser);
                break;
            case "changeData":
                stage.setScene(changeDataScene);
                notifyAllListeners("changeData", currentUser);
                break;
            case "addMovies":
                stage.setScene(addMovies);
                notifyAllListeners("addMovies", currentUser);
                break;
            case "updateMovies":
                stage.setScene(updateMovies);
                notifyAllListeners("updateMovies", currentUser);
                break;
            case "deleteMovies":
                stage.setScene(deleteMovies);
                notifyAllListeners("deleteMovies", currentUser);
                break;
        }
    }

    public static void changeScreen (String scr) {
        changeScreen(scr, null);
    }

    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen{
        void onScreenChanged(String newScreen, String currentUser);
    }

    public static void addOnChangesScreenListener (OnChangeScreen newListener){
        listeners.add(newListener);
    }

    private static void notifyAllListeners (String newScreen, String currentUser){
        for(OnChangeScreen o : listeners){
            o.onScreenChanged(newScreen, currentUser);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
