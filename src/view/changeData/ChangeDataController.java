package view.changeData;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.ModelUser;
import model.entity.User;
import utils.Dialog;
import view.principal.Main;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeDataController implements Initializable {

    @FXML
    private ImageView imgAlterarDados;

    @FXML
    private Rectangle rctBackground;

    @FXML
    private JFXTextField txtfUser;

    @FXML
    private JFXTextField txtfEmail;

    @FXML
    private JFXDatePicker dtBirthdate;

    @FXML
    private JFXPasswordField pfPass;

    @FXML
    private JFXPasswordField pfConfirmPass;

    @FXML
    private JFXButton btnAlterar;

    @FXML
    private JFXButton btnQuit;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangesScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, String currentUser) {
                    txtfUser.setText(ModelUser.getInstance().readUsersByEmail(currentUser).getName());
                    txtfEmail.setText(ModelUser.getInstance().readUsersByEmail(currentUser).getEmail());
                    dtBirthdate.setValue(ModelUser.getInstance().readUsersByEmail(currentUser).getBirthDate());
            }
        });
    }

    public void changeRegister(ActionEvent event) {
    }

    public void backHome(ActionEvent event) {Main.changeScreen("home");}



}
