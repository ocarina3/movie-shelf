package view.register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import view.principal.Main;

public class RegisterController {

    @FXML
    private JFXButton btnCadastrar;

    @FXML
    private Label lbCrieSuaConta;

    @FXML
    private JFXCheckBox cbxKids;

    @FXML
    private JFXTextField txtfUsername;

    @FXML
    private JFXTextField txtfTelefone;

    @FXML
    private JFXTextField txtfEmail;

    @FXML
    private JFXPasswordField passfSenha;

    @FXML
    private JFXPasswordField passfConfirmarSenha;

    @FXML
    private ImageView imgRegisterMan;

    @FXML
    private Label lbLogo;

    @FXML
    void onClick(ActionEvent event) {
        Main.changeScreen("main");
    }

}
