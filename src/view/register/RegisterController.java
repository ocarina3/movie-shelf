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

    @FXML
    void backHome(ActionEvent event) {
        Main.changeScreen("main");
        txtfUsername.setText("");
        txtfEmail.setText("");
        pfPass.setText("");
        pfConfirmPass.setText("");
        dtBirthDate.setValue(null);
    }

    @FXML
    void makeRegister(ActionEvent event) {

        if (txtfUsername.getText().equals("") || txtfEmail.getText().equals("") || pfPass.getText().equals("") || pfConfirmPass.getText().equals("") || dtBirthDate.getValue() == null) {
            Dialog.warning("Favor informar todos campos");
        }else{
            User user = new User(0, txtfUsername.getText(),txtfEmail.getText(), pfPass.getText(), dtBirthDate.getValue());
            boolean cadastro = ModelUser.getInstance().createClient(user);

            if(pfPass.getText().equals(pfConfirmPass.getText())){
                if(cadastro == false){
                    Dialog.error("E-mail ja cadastrado");
                } else {
                    txtfUsername.setText("");
                    txtfEmail.setText("");
                    pfPass.setText("");
                    pfConfirmPass.setText("");
                    dtBirthDate.setValue(null);
                    Dialog.information("Cadastro Conluído");
                    Main.changeScreen("main");
                }
            } else {
                Dialog.error("As senhas não coincidem");
            }
        }

    }

}
