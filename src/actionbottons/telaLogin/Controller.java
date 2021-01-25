package actionbottons.telaLogin;

import data.base.ReadDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entity.User;

public class Controller {
    public TextField emailLogin;
    public TextField passwordLogin;
    public Button verificarLogin;
    public Label labelLogin;

    private ReadDatabase readDatabase = new ReadDatabase();

    public void clicar(ActionEvent actionEvent) {
        String emailText = emailLogin.getText();

        try {
            User user = readDatabase.readUsersByEmail(emailText);
            if (user.getPassword().equals(passwordLogin.getText())) labelLogin.setText("entrou");
            else labelLogin.setText("senha invalida");
        }catch (Exception e){System.out.println("ERRO");}
    }
}
