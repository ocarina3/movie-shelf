package view.delete;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import model.ModelMovie;
import model.entity.Genre;
import model.entity.Movie;
import utils.Dialog;
import view.principal.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteMoviesController implements Initializable {

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
    private JFXTextField txtfGenre;

    String email;

    int movieId;

    @FXML
    void searchMovie(ActionEvent event) {
        for(Movie movie : ModelMovie.getInstance().readAllMovies()){
            if((movie.getName().equalsIgnoreCase(txtfSearch.getText()))){
                txtfName.setText(movie.getName());
                txtfDirector.setText(movie.getMovieDirector());
                txtaSinopse.setText(movie.getSynopsis());
                txtfMinAge.setText(Integer.toString(movie.getMinimumAge()));
                txtfGenre.setText(movie.getMovieGenre().getDescription());
                movieId = movie.getId();

            }
        }
    }

    @FXML
    public void deleteMovie(ActionEvent event) {

            Movie movie;
            if(txtfGenre.getText().equals(Genre.COMEDY.getDescription())) {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.COMEDY, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (txtfGenre.getText().equals(Genre.HORROR.getDescription())) {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.HORROR, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (txtfGenre.getText().equals(Genre.ACTION_ADVENTURE.getDescription())) {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.ACTION_ADVENTURE, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (txtfGenre.getText().equals(Genre.FANTASY.getDescription())) {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.FANTASY, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            } else if (txtfGenre.getText().equals(Genre.DRAMA.getDescription())) {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.DRAMA, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            }else {
                movie = new Movie(movieId, txtfName.getText(), txtfDirector.getText(), Genre.SCIENCE_FICTION, txtaSinopse.getText(), Integer.parseInt(txtfMinAge.getText()));
            }
            boolean delete = ModelMovie.getInstance().deleteMovieById(Integer.toString(movieId));
            if(delete == false){
                Dialog.error("Erro ao Excluir");
            } else {
                Dialog.information("Exclusão Conluída");
                txtfName.setText("");
                txtfDirector.setText("");
                txtaSinopse.setText("");
                txtfMinAge.setText("");
                txtfGenre.setText("");
                txtfSearch.setText("");
                movieId = 0;
                Main.changeScreen("deleteMovies", email);
            }
        }


    @FXML
    public void backHomeAdm(ActionEvent event) {  Main.changeScreen("adm", email);}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main.addOnChangesScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, String currentUser) {
                if(newScreen.equals("deleteMovies")) {
                    email = currentUser;
                }
            }
        });
    }
}
