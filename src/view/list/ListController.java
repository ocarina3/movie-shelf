package view.list;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.ModelUser;
import view.list.allmovies.AllMoviesController;
import view.list.favorite.FavoriteMoviesController;
import view.principal.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {

    public static String email;

    @FXML
    private Label lbLogo;

    @FXML
    private Label lbUsername;

    @FXML
    private JFXButton btnAllMovies;

    @FXML
    private JFXButton btnTerror;

    @FXML
    private JFXButton btnActionAdventure;

    @FXML
    private JFXButton btnDrama;

    @FXML
    private JFXButton btnComedy;

    @FXML
    private JFXButton btnFantasy;

    @FXML
    private JFXButton btnScienceFiction;

    @FXML
    private JFXButton btnFavorites;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXTextField txtfSearch;

    @FXML
    private ImageView imgSearch;

    @FXML
    private AnchorPane pnScreen;

    @FXML
    private JFXButton btnBestRatings;

    @FXML
    private JFXButton btnBack;


    /**
     * Controla as trocas de listas do programa, mostrando
     * a lista de filmes que o usuario desejar
     * */

    //retorna ao menu principal do tipo de usuario
    @FXML
    void back(ActionEvent event){
        if(ModelUser.getInstance().isAdmin(ModelUser.getInstance().readUsersByEmail(email)) == true){
            Main.changeScreen("adm", email);
        } else {
            Main.changeScreen("home", email);
        }
    }


    //muda para a lista de filmes mais bem avaliados
    @FXML
    void bestRatingsMovies(ActionEvent event) throws IOException {
        loadPage("bestratings/bestratings.fxml");
    }

    //muda para a lista de todos os filmes
    @FXML
    void allMovies(ActionEvent event) throws IOException {
        loadPage("allmovies/allmovies.fxml");
    }

    //muda para a tela de pesquisa de filmes
    @FXML
    void searchMovies(ActionEvent event) throws IOException {
        SearchMoviesController.search = txtfSearch.getText();
        loadPage("searchmovies.fxml");
    }

    //muda para a tela de filmes com o genero de drama
    @FXML
    void dramaMovies(ActionEvent event) throws IOException {
        loadPage("drama/dramamovies.fxml");
    }

    //muda para a tela de filmes com o genero de comedia
    @FXML
    void comedyMovies(ActionEvent event) throws IOException {
        loadPage("comedy/comedymovies.fxml");
    }

    //muda para a tela de filmes com o genero de terror
    @FXML
    void terrorMovies(ActionEvent event) throws IOException {
        loadPage("terror/terrormovies.fxml");
    }

    //muda para a tela de filmes com o genero de ação e aventura
    @FXML
    void actionandadventureMovies(ActionEvent event) throws IOException {
        loadPage("action/actionandadventuremovies.fxml");
    }

    //muda para a tela de filmes com o genero de fantasia
    @FXML
    void fantasyMovies(ActionEvent event) throws IOException {
        loadPage("fantasy/fantasymovies.fxml");
    }

    //muda para a tela de filmes com o genero de ficção
    @FXML
    void fictionMovies(ActionEvent event) throws IOException {
        loadPage("fiction/fictionmovies.fxml");
    }

    //muda para a tela de filmes favoritados pelo usuario
    @FXML
    void favoriteMovies(ActionEvent event) throws IOException {
        loadPage("favorite/favoritemovies.fxml");
    }

    //carrega a tela para selecionar
    private void loadPage(String page) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource(page));
        pnScreen.getChildren().setAll(root);
    }

    //incicializa a tela de escolher a lista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangesScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, String currentUser) {
                if(newScreen.equals("list")) {
                    lbUsername.setText(ModelUser.getInstance().readUsersByEmail(currentUser).getName());
                    email = currentUser;
                    FavoriteMoviesController.currentUserEmail = email;
                    AllMoviesController.currentEmail = currentUser;
                    try {
                        loadPage("allmovies/allmovies.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}
