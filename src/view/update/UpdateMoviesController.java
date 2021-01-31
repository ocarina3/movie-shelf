package view.update;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import model.ModelMovie;
import model.ModelUser;
import model.entity.Genre;
import model.entity.Movie;
import model.entity.User;
import utils.Dialog;
import view.principal.Main;

import java.net.URL;
import java.util.ResourceBundle;

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

    @FXML
    public void changeMovie(javafx.event.ActionEvent event) {
        if (txtfName.getText().equals("") || cbGenre.getValue().equals("") || txtfDirector.getText().equals("") || txtaSinopse.getText().equals("")) {
            utils.Dialog.warning("Favor informar todos campos");
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
            boolean update = ModelMovie.getInstance().updateMovie(movie);
            if(update == false){
                utils.Dialog.error("Erro ao atualizar");
            } else {
                utils.Dialog.information("Atualização Conluída");
                Main.changeScreen("login");
            }
        }
    }

    @FXML
    public void backHomeAdm(javafx.event.ActionEvent event) {  Main.changeScreen("adm");}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbGenre.getItems().add(Genre.COMEDY.getDescription());
        cbGenre.getItems().add(Genre.ACTION_ADVENTURE.getDescription());
        cbGenre.getItems().add(Genre.DRAMA.getDescription());
        cbGenre.getItems().add(Genre.SCIENCE_FICTION.getDescription());
        cbGenre.getItems().add(Genre.FANTASY.getDescription());
        cbGenre.getItems().add(Genre.HORROR.getDescription());
    }
}