package view.register;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.ModelUser;
import model.entity.User;
import utils.Dialog;
import utils.EncryptPassword;
import utils.ValidateEmail;
import view.principal.Main;

public class RegisterController {

    @FXML
    private JFXButton btnRegister;

    @FXML
    private Label lbCreateAccount;

    @FXML
    private JFXTextField txtfUsername;

    @FXML
    private JFXDatePicker dtBirthDate;

    @FXML
    private JFXTextField txtfEmail;

    @FXML
    private JFXPasswordField pfPass;

    @FXML
    private JFXPasswordField pfConfirmPass;

    @FXML
    private ImageView imgRegisterMan;

    @FXML
    private Label lbLogo;


    //Volta para o menu principal
    @FXML
    void backHome(ActionEvent event) {
        Main.changeScreen("main");
        txtfUsername.setText("");
        txtfEmail.setText("");
        pfPass.setText("");
        pfConfirmPass.setText("");
        dtBirthDate.setValue(null);
    }

    /**
     * Pega as informações da tela principal e, caso esteja com todas as informações
     * dentro dos criterios, cria um novo perfil.
     * */
    @FXML
    void makeRegister(ActionEvent event) {

        if (txtfUsername.getText().equals("") || txtfEmail.getText().equals("") || pfPass.getText().equals("") ||
                pfConfirmPass.getText().equals("") || dtBirthDate.getValue() == null) {
            Dialog.warning("Favor informar todos campos");
        } else if (!ValidateEmail.isValidEmail(txtfEmail.getText())) {
            Dialog.warning("Favor informar um endereço de E-mail válido:\nexemplo@exemplo.com");
        } else if (!pfPass.getText().equals(pfConfirmPass.getText())) {
            Dialog.error("As senhas não coincidem");
        } else {
            User user = new User(0, txtfUsername.getText(),txtfEmail.getText(), EncryptPassword.encryptPassword(txtfEmail.getText(), pfPass.getText()), dtBirthDate.getValue());
            boolean register = ModelUser.getInstance().createClient(user);
            if(!register){
                Dialog.error("E-mail já cadastrado");
            } else {
                txtfUsername.setText("");
                txtfEmail.setText("");
                pfPass.setText("");
                pfConfirmPass.setText("");
                dtBirthDate.setValue(null);
                Dialog.information("Cadastro Conluído");
                Main.changeScreen("main");
            }
        }

    }

}
