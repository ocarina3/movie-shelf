package view.delete;

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


    // Busca o filme e mostra na tela
    @FXML
    void searchMovie(ActionEvent event) {
        if(txtfSearch.getText() == ""){
            Dialog.warning("Campo de Pesquisa em Branco");
            return;
        }

        for(Movie movie : ModelMovie.getInstance().readAllMovies()){
            if((movie.getName().equalsIgnoreCase(txtfSearch.getText()))){
                txtfName.setText(movie.getName());
                txtfDirector.setText(movie.getMovieDirector());
                txtaSinopse.setText(movie.getSynopsis());
                txtfMinAge.setText(Integer.toString(movie.getMinimumAge()));
                if (txtfMinAge.getText().equals("0"))txtfMinAge.setText("L");
                txtfGenre.setText(movie.getMovieGenre().getDescription());
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

    /**
     * Recebe as informações pegas na Tela e tenta deleta o filme
     * */

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
                imgMovie.setFitWidth(75);
                imgMovie.setFitHeight(75);
                imgMovie.setLayoutX(110);
                imgMovie.setLayoutY(231);
                imgMovie.setImage(new Image (getClass().getResourceAsStream("../resources/plus.png")));
                movieId = 0;
                Main.changeScreen("deleteMovies", email);
            }
        }


    //Volta para o menu principal
    @FXML
    public void backHomeAdm(ActionEvent event) {  Main.changeScreen("adm", email);}

    //inicializa a tela de deletar filmes
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
