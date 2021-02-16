package utils;

import javafx.scene.control.Alert;

public class Dialog {
    /***
     * Aqui temos os Avisos, cada um podendo ser editado para passar uma certa mensagem
     */


    /**
     * Aqui mostra um aviso, utilizado geralmente para quando se tem falta de informação.
     * EX:
     *    não preencher todos os campos de algum cadastro.
     * */

    public static void warning(String message) {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Aviso");
        warning.setHeaderText(null);
        warning.setContentText(message);
        warning.showAndWait();
    }

    /**
     * Aqui mostra uma informação, utilizado para mostrar quando alguma tarefa foi concluida sem erros
     * EX:
     *    quando se conclui o cadastro de um filme
     * */

    public static void information(String message) {
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle("Informação");
        information.setHeaderText(null);
        information.setContentText(message);
        information.showAndWait();
    }

    /**
     * Aqui mostra quando se tem um erro, utilizado quando o dado passado não coincide com o do banco de dados
     * EX:
     *      email ou senha incorretos durante o login
     * */
    public static void error(String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Erro");
        error.setHeaderText(null);
        error.setContentText(message);
        error.showAndWait();
    }

}
