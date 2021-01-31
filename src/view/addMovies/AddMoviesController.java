package view.addMovies;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import view.principal.Main;

import java.awt.event.ActionEvent;

public class AddMoviesController {

    @FXML
    private JFXButton btnQuick;
    
    public void cadastrar(javafx.event.ActionEvent event) {
    }

    public void backHomeAdm(javafx.event.ActionEvent event) {  Main.changeScreen("adm");}
}
