package view.list;

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
import javafx.scene.input.MouseEvent;
import model.ModelMovie;
import model.ModelRating;
import model.ModelUser;
import model.entity.Movie;
import model.entity.User;
import org.controlsfx.control.Rating;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoMoviesController implements Initializable {

    public JFXToggleButton favoriteButton;

    public String email;

    public static int movieId;
    public Rating Rate;
    public Rating Rate1;

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







    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbTitle.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getName());
        lbGenre.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getMovieGenre().getDescription());
        txtaSinopse.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getSynopsis());
        lbDirector.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getMovieDirector());
        Image image = SwingFXUtils.toFXImage(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getImageBuffered(), null);
        imgMovie.setImage(image);
        this.email =ListController.email;
        User user = ModelUser.getInstance().readUsersByEmail(ListController.email);
        Movie movie = ModelMovie.getInstance().readMoviesById(Integer.toString(movieId));
        if(ModelUser.getInstance().isFavotited(user, movie)){
            favoriteButton.setSelected(true);
        }
        Rate1.setPartialRating(true);
        Rate1.setRating(ModelRating.getInstance().readAvgRatingByMovie(ModelMovie.getInstance().readMoviesById(String.format("%d",movieId)))/2);
        Rate.setPartialRating(true);

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

    public void getRate(MouseEvent mouseEvent) {

        Rate1.setRating(ModelRating.getInstance().readAvgRatingByMovie(ModelMovie.getInstance().readMoviesById(String.format("%d",movieId)))/2);

        float rate =(float)Rate.getRating()*2;
        BigDecimal bd = new BigDecimal(rate).setScale(1, RoundingMode.HALF_EVEN);

        ModelRating.getInstance().createRating(rate,
                ModelUser.getInstance().readUsersByEmail(email).getId(), movieId);

        //TODO dar update na avaliaçao
        System.out.println(bd);
    }
    public void getRate1(MouseEvent mouseEvent) {
        Rate1.setRating(ModelRating.getInstance().readAvgRatingByMovie(ModelMovie.getInstance().readMoviesById(String.format("%d",movieId)))/2);
    }
}
