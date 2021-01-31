package view.changedata;

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

    String email;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangesScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, String currentUser) {
                if(newScreen.equals("changeData")) {
                    txtfUser.setText(ModelUser.getInstance().readUsersByEmail(currentUser).getName());
                    txtfEmail.setText(ModelUser.getInstance().readUsersByEmail(currentUser).getEmail());
                    dtBirthdate.setValue(ModelUser.getInstance().readUsersByEmail(currentUser).getBirthDate());
                    pfPass.setText(ModelUser.getInstance().readUsersByEmail(currentUser).getPassword());
                    pfConfirmPass.setText(ModelUser.getInstance().readUsersByEmail(currentUser).getPassword());
                    email = currentUser;
                }
            }
        });
    }

    public void changeRegister(ActionEvent event) {
        if (txtfUser.getText().equals("") || txtfEmail.getText().equals("") || pfPass.getText().equals("") || pfConfirmPass.getText().equals("") || dtBirthdate.getValue() == null) {
            utils.Dialog.warning("Favor informar todos campos");
        }else{
            if(pfPass.getText().equals(pfConfirmPass.getText())){
                User user = new User(ModelUser.getInstance().readUsersByEmail(email).getId(), txtfUser.getText(),txtfEmail.getText(), pfPass.getText(), dtBirthdate.getValue());
                boolean update = ModelUser.getInstance().updateUser(user);
                if(update == false){
                    utils.Dialog.error("Erro ao atualizar");
                } else {
                    utils.Dialog.information("Cadastro Conluído");
                    Main.changeScreen("login");
                }
            } else {
                Dialog.error("As senhas não coincidem");
            }
        }
    }

    public void backHome(ActionEvent event) {
        Main.changeScreen("home", email);
    }



}
