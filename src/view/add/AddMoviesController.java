package view.add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddMoviesController implements Initializable {

    /**
     * controla a tela de adicionar filmes
     * */

    public BufferedImage bufferedImage;

    @FXML
    private JFXButton btnQuick;

    @FXML
    private JFXTextArea txtaSinopse;

    @FXML
    private JFXTextField txtfName;

    @FXML
    private JFXTextField txtfDirector;

    @FXML
    private JFXComboBox<String> cbGenre;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private JFXButton btnAddImg;

    @FXML
    private JFXButton btnQuit;

    @FXML
    private JFXTextField txtfMinAge;

    @FXML
    private ImageView imgMovie;

    String email;

    int movieId;


    /**
     * Recebe as informações pegas na Tela de Adicionar filme e tenta
     * criar um filme mandando para o ModelMovie
     * */
    @FXML
    void createMovie(ActionEvent event) {

        //verifica se todos os campos estão preenchidos
        if (txtfName.getText().equals("") || txtfDirector.equals("") || txtaSinopse.getText().equals("")
                || cbGenre.getValue().equals("") || bufferedImage == null) {
            Dialog.warning("Favor informar todos campos");
        }else{
            Movie movie;
            if(cbGenre.getValue().equals(Genre.COMEDY.getDescription())) {
                 movie = new Movie(txtfName.getText(), txtfDirector.getText(), Genre.COMEDY, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (cbGenre.getValue().equals(Genre.HORROR.getDescription())) {
                 movie = new Movie(txtfName.getText(), txtfDirector.getText(), Genre.HORROR, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (cbGenre.getValue().equals(Genre.ACTION_ADVENTURE.getDescription())) {
                 movie = new Movie(txtfName.getText(), txtfDirector.getText(), Genre.ACTION_ADVENTURE, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (cbGenre.getValue().equals(Genre.FANTASY.getDescription())) {
                 movie = new Movie(txtfName.getText(), txtfDirector.getText(), Genre.FANTASY, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (cbGenre.getValue().equals(Genre.DRAMA.getDescription())) {
                 movie = new Movie(txtfName.getText(), txtfDirector.getText(), Genre.DRAMA, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            }else {
                 movie = new Movie(txtfName.getText(), txtfDirector.getText(), Genre.SCIENCE_FICTION, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            }

            movie.setImageBuffered(bufferedImage);

            //Tenta criar o filme
            boolean cadastro = ModelMovie.getInstance().createMovie(movie);

            //Caso de Erro
            if(cadastro == false){
                Dialog.error("Filme ja cadastrado");
            } else {
                txtaSinopse.setText("");
                txtfDirector.setText("");
                txtfName.setText("");
                txtfMinAge.setText("");
                cbGenre.setValue("Gênero");
                imgMovie.setFitWidth(75);
                imgMovie.setFitHeight(75);
                imgMovie.setLayoutX(110);
                imgMovie.setLayoutY(231);
                imgMovie.setImage(new Image (getClass().getResourceAsStream("../resources/plus.png")));
                Dialog.information("Cadastro Conluído");
                Main.changeScreen("addMovies", email);
            }
        }
    }

    /**
     * Função criada para carregar imagens do tipo png e jpg
     * */

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
        } catch (IOException | IllegalArgumentException ex) {
            Logger.getLogger(AdmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Volta para o menu principal
    @FXML
    public void backHomeAdm(javafx.event.ActionEvent event) {
        Main.changeScreen("adm", email);
    }

    //inicializa a tela de Adicionar filmes
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
                if(newScreen.equals("addMovies")) {
                    email = currentUser;
                }
            }
        });
    }
}
