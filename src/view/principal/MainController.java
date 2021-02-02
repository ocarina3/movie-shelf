package view.principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;

public class MainController {

    @FXML
    private Label lbLogo;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lbWelcome;

    @FXML
    private Label lbApresentacao;

    @FXML
    private Label lbServicos;

    @FXML
    private Line lineWelcome;

    @FXML
    private Button btnRegister;

    @FXML
    void onClick(ActionEvent event) {
        Main.changeScreen("register");
    }

    @FXML
    void clickLogin(ActionEvent event) {Main.changeScreen("login"); }
}
