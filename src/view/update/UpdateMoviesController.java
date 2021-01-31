package view.update;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import view.principal.Main;

public class UpdateMoviesController {

    @FXML
    private ImageView imgMovie;

    @FXML
    private JFXTextArea txtaSinopse;

    @FXML
    private JFXTextField txtfName;

    @FXML
    private JFXTextField txtfGenre;

    @FXML
    private JFXTextField txtfRate;

    @FXML
    private JFXButton btnChange;

    @FXML
    private JFXButton btnQuick;

    @FXML
    private JFXTextField txtfSearch;

    @FXML
    public void changeMovie(javafx.event.ActionEvent event) {

    }

    @FXML
    public void backHomeAdm(javafx.event.ActionEvent event) {  Main.changeScreen("adm");}
}
