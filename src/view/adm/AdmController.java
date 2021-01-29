package view.adm;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import view.principal.Main;

public class AdmController {

    @FXML
    private Rectangle rctBackgroung;

    @FXML
    private Text txtWelcomeMaster;

    @FXML
    private ImageView imgLock;

    @FXML
    private JFXButton btnListMovies;

    @FXML
    private Line lineListMovies;

    @FXML
    private JFXButton btnAddMovies;

    @FXML
    private Line lineAddMovies;

    @FXML
    private JFXButton btnUpdateMovies;

    @FXML
    private JFXButton btnRemoveMovies;

    @FXML
    private Line lineRemoveMovies;

    @FXML
    private ImageView imgAddMovies;

    @FXML
    private ImageView imgListMovies;

    @FXML
    private ImageView imgUpdateMovies;

    @FXML
    private ImageView imgRemoveMovies;

    @FXML
    private JFXButton btnQuit;

    @FXML
    private ImageView imgQuit;

    @FXML
    private JFXButton btnChangeDatas;

    @FXML
    private ImageView imgChangeDatas;

    @FXML
    void backLogin(ActionEvent event) {
        Main.changeScreen("login");
    }

}