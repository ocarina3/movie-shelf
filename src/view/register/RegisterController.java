package view.register;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.principal.Main;

public class RegisterController {

    @FXML
    private JFXButton btnVoltar;

    @FXML
    void onClick(ActionEvent event) {
        Main.changeScreen("main");
    }

}
