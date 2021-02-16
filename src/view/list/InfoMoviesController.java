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

    public Rating userRating;

    public Rating avgRating;

    public Label lbIdade;

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

    User user = ModelUser.getInstance().readUsersByEmail(ListController.email);
    Movie movie = ModelMovie.getInstance().readMoviesById(Integer.toString(movieId));

    /**
     * Inicializa a Tela De informações do filme clicado em uma das telas de listagem
     * Permitindo Avaliar e Favoritar o filme
     * */

    //inicializa a tela e todos os dados ja cadastrados do filme
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbTitle.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getName());
        lbGenre.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getMovieGenre().getDescription());
        txtaSinopse.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getSynopsis());
        lbDirector.setText(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getMovieDirector());
        Image image = SwingFXUtils.toFXImage(ModelMovie.getInstance().readMoviesById(Integer.toString(movieId)).getImageBuffered(), null);
        imgMovie.setImage(image);
        lbIdade.setText(Integer.toString(movie.getMinimumAge()));
        if (movie.getMinimumAge() == 0) lbIdade.setText("L");
        this.email =ListController.email;


        if(ModelUser.getInstance().isFavotited(user, movie)){
            favoriteButton.setSelected(true);
        }

        float initialRating = 0;
        if(ModelRating.getInstance().readRatingByUserAndMovie(movie, user) != null) {
            initialRating = ModelRating.getInstance().readRatingByUserAndMovie(movie, user).getRating()/2;
        }

        userRating.setPartialRating(true);
        userRating.setRating(initialRating);

        avgRating.setPartialRating(true);
        avgRating.setRating(ModelRating.getInstance().readAvgRatingByMovie(ModelMovie.getInstance().readMoviesById(String.format("%d",movieId)))/2);
    }


    //Implementa a função de adicionar aos favoritos
    public void addFavorite(ActionEvent actionEvent) {
        User user = ModelUser.getInstance().readUsersByEmail(ListController.email);
        Movie movie = ModelMovie.getInstance().readMoviesById(Integer.toString(movieId));
        boolean i = ModelUser.getInstance().isFavotited(user,movie);

        if (!(ModelUser.getInstance().isFavotited(user,movie))) {
            ModelUser.getInstance().favoriteMovies(user,movie);
        }
        else ModelUser.getInstance().deleteFavoriteMovies(user,movie);
    }

    //implementa a avaliação do usuario no programa
    public void getUserRating() {

        float rate =(float) userRating.getRating()*2;
        BigDecimal bd = new BigDecimal(rate).setScale(1, RoundingMode.HALF_EVEN);

        if(!ModelRating.getInstance().createRating(bd.floatValue(), ModelUser.getInstance().readUsersByEmail(email).getId(), movieId)) {
            ModelRating.getInstance().updateRatingValueById(bd.floatValue(), ModelRating.getInstance().readRatingByUserAndMovie(movie, user).getId());
        }
    }

    //mostra a media de notas do programa
    public void getAvgRating(MouseEvent mouseEvent) {
        avgRating.setRating(ModelRating.getInstance().readAvgRatingByMovie(ModelMovie.getInstance().readMoviesById(String.format("%d",movieId)))/2);
    }
}
