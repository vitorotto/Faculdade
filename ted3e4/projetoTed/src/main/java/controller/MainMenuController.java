package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller class for the main menu of the application.
 * <p>
 * This class handles user interactions with the main menu, providing methods to open various account-related views such as inclusion, consultation, alteration, deletion, listing, deposit, withdrawal, and statement operations.
 * </p>
 */
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

    public void onListar(ActionEvent event) {
        openListView();
    }

    public void onDepositar(ActionEvent event) {
        openOperationView("Realizar Depósito",
                new AccountOperationController(AccountOperationController.OperationType.DEPOSIT));
    }

    public void onSacar(ActionEvent event) {
        openOperationView("Realizar Saque",
                new AccountOperationController(AccountOperationController.OperationType.WITHDRAW));
    }

    public void onExtrato(ActionEvent event) {
        openOperationView("Extrato",
                new AccountOperationController(AccountOperationController.OperationType.STATEMENT));
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

    private void openListView() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/AccountListView.fxml")
            );
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Listar Contas");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (Exception exception) {
            showError("Não foi possível abrir a listagem de contas.");
        }
    }

    private void openOperationView(String title, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/AccountOperationView.fxml")
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
            showError("Não foi possível abrir " + title.toLowerCase() + ".");
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
