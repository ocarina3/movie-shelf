package view.home;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.ModelUser;
import view.login.LoginController;
import view.principal.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label lbUsernameTop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangesScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, String currentUser) {
                if(newScreen.equals("home")) {
                    lbUsernameTop.setText(ModelUser.getInstance().readUsersByEmail(currentUser).getName());
                }
            }
        });
    }
}
