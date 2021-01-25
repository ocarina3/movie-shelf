package actionbottons.telaCriarPerfil;

import data.base.CreateDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.ModelUser;
import model.entity.User;

import java.sql.Date;
import java.time.LocalDate;

public class Controller {
    public TextField emailCriarPerfil;
    public TextField senhaCriarPerfil;
    public TextField nomeCriarPerfil;
    public DatePicker dataCriarPerfil;
    public Button botaoCriarPerfil;

    public void clicar(ActionEvent actionEvent) {
        CreateDatabase createDatabase = new CreateDatabase();

        LocalDate localDate = dataCriarPerfil.getValue();
        String email = emailCriarPerfil.getText();
        String senha = senhaCriarPerfil.getText();
        String nome = nomeCriarPerfil.getText();

        User user = new User(0,nome,email,senha,localDate);

        ModelUser.getInstance().createClient(user);
    }
}
