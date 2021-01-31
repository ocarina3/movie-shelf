package view.adm;

import com.jfoenix.controls.JFXButton;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import view.principal.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdmController {

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

    @FXML
    void addMovie(ActionEvent event){Main.changeScreen("addMovies");}

    @FXML
    void backLogin(ActionEvent event) {
        Main.changeScreen("login");
    }

    @FXML
    void updateMovie(ActionEvent event) {
        Main.changeScreen("updateMovies");
    }

    @FXML
    void loadImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG =
                new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg =
                new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG =
                new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng =
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageIcon myImageView;
            imgLock.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(AdmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}