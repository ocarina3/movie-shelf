package utils;

import javafx.scene.control.Alert;

public class Dialog {

    public static void warning(String message) {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Aviso");
        warning.setHeaderText(null);
        warning.setContentText(message);
        warning.showAndWait();
    }

    public static void information(String message) {
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle("Informação");
        information.setHeaderText(null);
        information.setContentText(message);
        information.showAndWait();
    }

    public static void error(String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Informação");
        error.setHeaderText(null);
        error.setContentText(message);
        error.showAndWait();
    }

}
