package view.adm;

import com.jfoenix.controls.JFXButton;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.ModelUser;
import view.principal.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdmController implements Initializable {

    @FXML
    private Rectangle rctBackgroung;

    @FXML
    private Text txtWelcomeMaster;

    @FXML
    private ImageView imgLock;

    @FXML
    private JFXButton btnListMovies;

    @FXML
    private Line lineListMovies;

    @FXML
    private JFXButton btnAddMovies;

    @FXML
    private Line lineAddMovies;

    @FXML
    private JFXButton btnUpdateMovies;

    @FXML
    private JFXButton btnRemoveMovies;

    @FXML
    private Line lineRemoveMovies;

    @FXML
    private ImageView imgAddMovies;

    @FXML
    private ImageView imgListMovies;

    @FXML
    private ImageView imgUpdateMovies;

    @FXML
    private ImageView imgRemoveMovies;

    @FXML
    private JFXButton btnQuit;

    @FXML
    private ImageView imgQuit;

    @FXML
    private JFXButton btnChangeDatas;

    @FXML
    private ImageView imgChangeDatas;

    @FXML
    private JFXButton btnSecret;

    String email;

    @FXML
    void listMovies(ActionEvent event) {
        Main.changeScreen("list", email);
    }

    @FXML
    void addMovie(ActionEvent event){Main.changeScreen("addMovies", email);}

    @FXML
    void backLogin(ActionEvent event) {
        Main.changeScreen("login");
    }

    @FXML
    void updateMovie(ActionEvent event) {
        Main.changeScreen("updateMovies", email);
    }

    @FXML
    void removeMovie(ActionEvent event) {
        Main.changeScreen("deleteMovies", email);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangesScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, String currentUser) {
                if(newScreen.equals("adm")) {
                    email = currentUser;
                }
            }
        });
    }
}