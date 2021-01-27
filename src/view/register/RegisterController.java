package view.register;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.ModelUser;
import model.entity.User;
import view.principal.Main;

import java.time.LocalDate;

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
    private JFXDatePicker dtAnoNascimento;

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

        if (txtfUsername.getText().equals("") || txtfEmail.getText().equals("") || passfSenha.getText().equals("") || passfConfirmarSenha.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Favor informar todos campos");;
            alert.show();
        }else{
            User user = new User(0, txtfUsername.getText(),txtfEmail.getText(),passfSenha.getText(), dtAnoNascimento.getValue());
            boolean cadastro = ModelUser.getInstance().createClient(user);

            if(passfSenha.getText().equals(passfConfirmarSenha.getText())){
                if(cadastro == false){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("E-mail ja cadastrado");;
                    alert.show();
                } else {
                    txtfUsername.setText("");
                    txtfEmail.setText("");
                    passfSenha.setText("");
                    passfConfirmarSenha.setText("");
                    dtAnoNascimento.setValue(null);
                    Main.changeScreen("main");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("As senhas não coincidem");;
                alert.show();
            }
        }

    }

}
