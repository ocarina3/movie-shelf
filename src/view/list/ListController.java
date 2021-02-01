package view.list;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import view.list.favorite.FavoriteMoviesController;
import view.principal.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {

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
    void allMovies(ActionEvent event) throws IOException {
        loadPage("allmovies/allmovies.fxml");
    }

    @FXML
    void searchMovies(ActionEvent event) throws IOException {
        SearchMoviesController.search = txtfSearch.getText();
        loadPage("searchmovies.fxml");
    }

    @FXML
    void dramaMovies(ActionEvent event) throws IOException {
        loadPage("drama/dramamovies.fxml");
    }

    @FXML
    void comedyMovies(ActionEvent event) throws IOException {
        loadPage("comedy/comedymovies.fxml");
    }

    @FXML
    void terrorMovies(ActionEvent event) throws IOException {
        loadPage("terror/terrormovies.fxml");
    }

    @FXML
    void actionandadventureMovies(ActionEvent event) throws IOException {
        loadPage("action/actionandadventuremovies.fxml");
    }

    @FXML
    void fantasyMovies(ActionEvent event) throws IOException {
        loadPage("fantasy/fantasymovies.fxml");
    }

    @FXML
    void fictionMovies(ActionEvent event) throws IOException {
        loadPage("fiction/fictionmovies.fxml");
    }

    @FXML
    void favoriteMovies(ActionEvent event) throws IOException {
        loadPage("favorite/favoritemovies.fxml");
    }

    private void loadPage(String page) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource(page));
        pnScreen.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangesScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, String currentUser) {
                if(newScreen.equals("list")) {
                    lbUsername.setText(ModelUser.getInstance().readUsersByEmail(currentUser).getName());
                    FavoriteMoviesController.currentUserEmail = currentUser;
                }
            }
        });

        try {
            loadPage("allmovies/allmovies.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
