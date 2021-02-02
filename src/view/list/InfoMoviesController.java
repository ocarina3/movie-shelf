package view.list;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ModelMovie;
import model.ModelRating;
import model.ModelUser;
import model.entity.Movie;
import model.entity.Rating;
import model.entity.User;
import utils.Dialog;
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
    private JFXTextField txtfRate;

    @FXML
    private Label lbDirector;

    @FXML
    private Label lbRate;

    @FXML
    void makeRate(ActionEvent event) {
        float Rate;
        if(txtfRate.getText().equals("") || campoNumerico(txtfRate.getText()) == false){
            Dialog.error("Favor informar corretamente");
        } else {
            if(Float.parseFloat(txtfRate.getText()) > 10){
                Rate = 10;
                Rating rt = new Rating(0, Rate, ModelUser.getInstance().readUsersByEmail(ListController.email).getId(), movieId);
                ModelRating.getInstance().createRating(rt);
            } else if(Float.parseFloat(txtfRate.getText()) < 0){
                Rate = 0;
                Rating rt = new Rating(0, Rate, ModelUser.getInstance().readUsersByEmail(ListController.email).getId(), movieId);
                ModelRating.getInstance().createRating(rt);
            } else {
                Rate = Float.parseFloat(txtfRate.getText());
                Rating rt = new Rating(0, Rate, ModelUser.getInstance().readUsersByEmail(ListController.email).getId(), movieId);
                ModelRating.getInstance().createRating(rt);
            }
            Dialog.information("Avaliação Recolhida");
        }
    }

    private boolean campoNumerico(String campo){
        return campo.matches("[+-]?([0-9]*[.])?[0-9]+");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbTitle.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getName());
        lbGenre.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getMovieGenre().getDescription());
        txtaSinopse.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getSynopsis());
        lbDirector.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getMovieDirector());
        lbRate.setText(Float.toString(ModelRating.getInstance().readAvgRatingByMovie(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)))));
        Image image = SwingFXUtils.toFXImage(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getImageBuffered(), null);
        imgMovie.setImage(image);

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
