package view.list;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class AllMoviesController implements Initializable {

    @FXML
    private Pane pnMovies;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbGender;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int posX = 74;
        int posY = 155;
        for(int i=0;i<16;i++){
            if(posX <= 788){
                Pane pnImg = new Pane();
                pnImg.setLayoutX(posX);
                pnImg.setLayoutY(posY);
                pnImg.setPrefSize(185, 238);
                pnImg.setId("pnImg");
                pnMovies.getChildren().add(pnImg);
                Pane pnRate = new Pane();
                pnRate.setPrefSize(63, 32);
                pnRate.setLayoutX(posX + 65);
                pnRate.setLayoutY(posY - 21);
                pnRate.setId("pnRate");
                pnMovies.getChildren().add(pnRate);
                Label lbRate = new Label();
                lbRate.setPrefSize(63, 32);
                lbRate.setLayoutX(posX + 80);
                lbRate.setLayoutY(posY - 21);
                lbRate.setId("lbRate");
                lbRate.setText("Nota"+i);
                pnMovies.getChildren().add(lbRate);
                Label lbTitle = new Label();
                lbTitle.setPrefSize(63, 32);
                lbTitle.setLayoutX(posX + 5);
                lbTitle.setLayoutY(posY + 264);
                lbTitle.setPrefSize(170, 29);
                lbTitle.setId("lbTitle");
                lbTitle.setText("Titulo"+i);
                pnMovies.getChildren().add(lbTitle);
                Label lbGender = new Label();
                lbGender.setPrefSize(63, 32);
                lbGender.setLayoutX(posX + 5);
                lbGender.setLayoutY(posY + 300);
                lbGender.setPrefSize(90, 23);
                lbGender.setId("lbGender");
                lbGender.setText("Gênero"+i);
                pnMovies.getChildren().add(lbGender);
                posX += 238;
            } else {
                posY = 155 + (376 * ((i + 1) / 4));
                posX = 74;
                i--;
            }
        }
    }
}
