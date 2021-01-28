package view.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.principal.Main;

public class LoginController {

    @FXML
    void home(ActionEvent event) {
        Main.changeScreen("main"); }
}
