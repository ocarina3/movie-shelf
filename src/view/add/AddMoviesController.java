package view.add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.ModelMovie;
import model.ModelUser;
import model.entity.Genre;
import model.entity.Movie;
import model.entity.User;
import utils.Dialog;
import view.principal.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddMoviesController implements Initializable {

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
    private JFXButton btnQuit;

    @FXML
    private JFXTextField txtfMinAge;

    String email;

    int movieId;

    @FXML
    void createMovie(ActionEvent event) {
        if (txtfName.getText().equals("") || txtfDirector.equals("") || txtaSinopse.getText().equals("")|| cbGenre.getValue().equals("")) {
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

            System.out.println(movie.toString());
            boolean cadastro = ModelMovie.getInstance().createMovie(movie);

            if(cadastro == false){
                Dialog.error("Filme ja cadastrado");
            } else {
                txtaSinopse.setText("");
                txtfDirector.setText("");
                txtfName.setText("");
                txtfMinAge.setText("");
                cbGenre.setValue("Gênero");
                Dialog.information("Cadastro Conluído");
                Main.changeScreen("addMovies");
            }
        }
    }

    @FXML
    public void backHomeAdm(javafx.event.ActionEvent event) {

        Main.changeScreen("adm", email);
    }

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
