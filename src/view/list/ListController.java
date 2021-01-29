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
    private JFXButton btnAction;

    @FXML
    private JFXButton btnAdventure;

    @FXML
    private JFXButton btnComedy;

    @FXML
    private JFXButton btnThriller;

    @FXML
    private JFXButton btnFavorites;

    @FXML
    private JFXButton btnBestRatings;

    @FXML
    private JFXTextField txtfSearch;

    @FXML
    private ImageView imgSearch;

    @FXML
    private AnchorPane pnScreen;

    @FXML
    void allMovies(ActionEvent event) throws IOException {
        loadPage("allmovies.fxml");
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
                }
            }
        });


    }
}
