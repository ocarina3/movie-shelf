package view.list;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import model.ModelMovie;
import model.ModelUser;
import model.entity.Movie;
import model.entity.User;
import view.principal.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoMoviesController implements Initializable {

    public JFXToggleButton favoriteButton;

    public String email;

    public static int movieId;

    @FXML
    private ImageView imgMovie;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbGenre;

    @FXML
    private TextArea txtaSinopse;

    @FXML
    private Label lbSinopse;

    @FXML
    private Label lbRate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbTitle.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getName());
        lbGenre.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getMovieGenre().getDescription());
        txtaSinopse.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getSynopsis());
        lbRate.setText("10");

        User user = ModelUser.getInstance().readUsersByEmail(ListController.email);
        Movie movie = ModelMovie.getInstance().readMoviesById(Integer.toString(movieId));
        if(ModelUser.getInstance().isFavotited(user, movie)){
            favoriteButton.setSelected(true);
        }

    }

    public void addFavorite(ActionEvent actionEvent) {
        User user = ModelUser.getInstance().readUsersByEmail(ListController.email);
        Movie movie = ModelMovie.getInstance().readMoviesById(Integer.toString(movieId));
        boolean i = ModelUser.getInstance().isFavotited(user,movie);

        if (!(ModelUser.getInstance().isFavotited(user,movie))) {
            ModelUser.getInstance().favoriteMovies(user,movie);
        }
        else ModelUser.getInstance().deleteFavoriteMovies(user,movie);
    }
}
