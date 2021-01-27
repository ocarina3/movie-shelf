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
    void onClick(ActionEvent event) {

        if (txtfUsername.getText().equals("") || txtfEmail.getText().equals("") || pfPass.getText().equals("") || pfConfirmPass.getText().equals("") || dtBirthDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Favor informar todos campos");;
            alert.show();
        }else{
            User user = new User(0, txtfUsername.getText(),txtfEmail.getText(), pfPass.getText(), dtBirthDate.getValue());
            boolean cadastro = ModelUser.getInstance().createClient(user);

            if(pfPass.getText().equals(pfConfirmPass.getText())){
                if(cadastro == false){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("E-mail ja cadastrado");;
                    alert.show();
                } else {
                    txtfUsername.setText("");
                    txtfEmail.setText("");
                    pfPass.setText("");
                    pfConfirmPass.setText("");
                    dtBirthDate.setValue(null);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Cadastro Conluído");;
                    alert.show();
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
