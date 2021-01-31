package view.home;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import model.ModelUser;
import view.principal.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label lbUsername;

    @FXML
    private Label lbWelcomeAgain;

    @FXML
    private Button btnHome;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Line lineLogo;

    @FXML
    private Label lbLogo;

    @FXML
    private JFXButton btnQuit;

    @FXML
    private JFXButton btnChangeUser;

    String email;

    @FXML
    void backLogin(ActionEvent event) {
        Main.changeScreen("login");
    }

    @FXML
    void goList(ActionEvent event) {
        Main.changeScreen("list", email);
    }

    @FXML
    void changeData(ActionEvent event){
        Main.changeScreen("changeData", email);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangesScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, String currentUser) {
                if(newScreen.equals("home")) {
                    lbUsername.setText(ModelUser.getInstance().readUsersByEmail(currentUser).getName());
                    email = currentUser;
                }
            }
        });
    }
}
