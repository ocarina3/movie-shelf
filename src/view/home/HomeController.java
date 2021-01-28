package view.home;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.entity.CurrentUser;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label lbUsernameTop;

    public void initialize(URL location, ResourceBundle resources) {
        lbUsernameTop.setText(CurrentUser.currentUser.getName());
    }
}
