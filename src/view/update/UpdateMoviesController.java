package view.update;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.ModelMovie;
import model.ModelUser;
import model.entity.Genre;
import model.entity.Movie;
import model.entity.User;
import utils.Dialog;
import view.adm.AdmController;
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

public class UpdateMoviesController implements Initializable {

    @FXML
    private ImageView imgMovie;

    @FXML
    private JFXTextArea txtaSinopse;

    @FXML
    private JFXTextField txtfName;

    @FXML
    private JFXTextField txtfMinAge;

    @FXML
    private JFXTextField txtfDirector;

    @FXML
    private JFXButton btnChange;

    @FXML
    private JFXButton btnQuit;

    @FXML
    private JFXTextField txtfSearch;

    @FXML
    private JFXComboBox<String> cbGenre;

    String email;

    int movieId;

    public BufferedImage bufferedImage;

    @FXML
    void searchMovie(ActionEvent event) {
        for(Movie movie : ModelMovie.getInstance().readAllMovies()){
            if((movie.getName().equalsIgnoreCase(txtfSearch.getText()))){
                txtfName.setText(movie.getName());
                txtfDirector.setText(movie.getMovieDirector());
                txtaSinopse.setText(movie.getSynopsis());
                txtfMinAge.setText(Integer.toString(movie.getMinimumAge()));
                cbGenre.setValue(movie.getMovieGenre().getDescription());
                movieId = movie.getId();
                Image image = SwingFXUtils.toFXImage(movie.getImageBuffered(), null);
                imgMovie.setFitHeight(260);
                imgMovie.setFitWidth(200);
                imgMovie.setLayoutX(55);
                imgMovie.setLayoutY(150);
                imgMovie.setImage(image);

            }
        }
    }

    @FXML
    public void changeMovie(javafx.event.ActionEvent event) {
        if (txtfName.getText().equals("") || cbGenre.getValue().equals("") || txtfDirector.getText().equals("") || txtaSinopse.getText().equals("")) {
            utils.Dialog.warning("Favor informar todos campos");
        }else{
            Movie movie;
            if(cbGenre.getValue().equals(Genre.COMEDY.getDescription())) {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.COMEDY, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (cbGenre.getValue().equals(Genre.HORROR.getDescription())) {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.HORROR, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (cbGenre.getValue().equals(Genre.ACTION_ADVENTURE.getDescription())) {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.ACTION_ADVENTURE, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (cbGenre.getValue().equals(Genre.FANTASY.getDescription())) {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.FANTASY, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (cbGenre.getValue().equals(Genre.DRAMA.getDescription())) {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.DRAMA, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            }else {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.SCIENCE_FICTION, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            }
            movie.setImageBuffered(bufferedImage);
            boolean update = ModelMovie.getInstance().updateMovie(movie);
            if(update == false){
                utils.Dialog.error("Erro ao atualizar");
            } else {
                utils.Dialog.information("Atualização Conluída");
                txtfName.setText("");
                txtfDirector.setText("");
                txtaSinopse.setText("");
                txtfMinAge.setText("");
                cbGenre.setValue("Gênero");
                txtfSearch.setText("");
                imgMovie.setFitWidth(75);
                imgMovie.setFitHeight(75);
                imgMovie.setLayoutX(110);
                imgMovie.setLayoutY(231);
                imgMovie.setImage(new Image (getClass().getResourceAsStream("../resources/plus.png")));
                movieId = 0;
                Main.changeScreen("updateMovies", email);
            }
        }
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
            bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageIcon myImageView;
            imgMovie.setFitHeight(260);
            imgMovie.setFitWidth(200);
            imgMovie.setLayoutX(55);
            imgMovie.setLayoutY(150);
            imgMovie.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(AdmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void backHomeAdm(javafx.event.ActionEvent event) {  Main.changeScreen("adm", email);}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbGenre.getItems().add(Genre.COMEDY.getDescription());
        cbGenre.getItems().add(Genre.ACTION_ADVENTURE.getDescription());
        cbGenre.getItems().add(Genre.DRAMA.getDescription());
        cbGenre.getItems().add(Genre.SCIENCE_FICTION.getDescription());
        cbGenre.getItems().add(Genre.FANTASY.getDescription());
        cbGenre.getItems().add(Genre.HORROR.getDescription());
        Main.addOnChangesScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, String currentUser) {
                if(newScreen.equals("updateMovies")) {
                    email = currentUser;
                }
            }
        });
    }
}
