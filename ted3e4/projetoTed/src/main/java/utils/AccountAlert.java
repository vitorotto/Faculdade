package utils;

import javafx.scene.control.Alert;

/**
 * Utilitario responsavel por exibir mensagens padronizadas em telas JavaFX.
 */
public class AccountAlert {
    /**
     * Exibe uma janela de alerta e aguarda a resposta do usuario.
     *
     * @param type tipo visual do alerta
     * @param title titulo exibido na janela
     * @param message mensagem apresentada ao usuario
     */
    public static void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
