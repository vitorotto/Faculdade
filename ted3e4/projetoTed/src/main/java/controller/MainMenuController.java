package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {
    public void onIncluir(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/AccountRegisterView.fxml")
            );
            loader.setController(new AccountInsertController());
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Incluir Conta");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (Exception exception) {
            showError("Não foi possível abrir a tela de inclusão.");
        }
    }

    public void onConsultar(ActionEvent event) {
        openAccountView("Consultar Conta", new AccountConsultController());
    }

    public void onAlterar(ActionEvent event) {
        openAccountView("Alterar Conta", new AccountUpdateController());
    }

    public void onExcluir(ActionEvent event) {
        openAccountView("Excluir Conta", new AccountDeleteController());
    }

    private void openAccountView(String title, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/AccountRegisterView.fxml")
            );
            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (Exception exception) {
            showError("Não foi possível abrir a tela de " + title.toLowerCase() + ".");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
