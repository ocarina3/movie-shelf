package view.login;

import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.ModelUser;
import utils.Dialog;
import view.principal.Main;

public class LoginController {

    @FXML
    private Rectangle rectBackground;

    @FXML
    private ImageView imgWaves;

    @FXML
    private Text txtLogin;

    @FXML
    private JFXTextField txtfEmail;

    @FXML
    private JFXPasswordField pfPass;

    @FXML
    private Text txtLoginToContinue;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView imgUser;

    @FXML
    private ImageView imgPass;

    @FXML
    void backHome(ActionEvent event) {
        Main.changeScreen("main");
        txtfEmail.setText("");
        pfPass.setText("");
    }

    @FXML
    void makeLogin(ActionEvent event) {
        if(ModelUser.getInstance().readUsersByEmail(txtfEmail.getText()) == null){
            Dialog.error("Usuário ou senha incorreto(s)");
        } else {
            if((ModelUser.getInstance().readUsersByEmail(txtfEmail.getText()).getPassword()).equals(pfPass.getText())) {
                Main.changeScreen("home");
                txtfEmail.setText("");
                pfPass.setText("");
            } else {
                Dialog.error("Usuário ou senha incorreto(s)");
            }

        }
    }
}
