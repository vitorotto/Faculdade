package controller;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Account;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountDeleteController implements Initializable {
    @FXML private GridPane formGrid;
    @FXML private Label searchLabel;
    @FXML private HBox searchRow;
    @FXML private TextField idField;
    @FXML private TextField numberField;
    @FXML private TextField ownerField;
    @FXML private TextField balanceField;
    @FXML private Button loadButton;
    @FXML private Button cancelButton;
    @FXML private Button actionButton;

    private Account loadedAccount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formGrid.getChildren().removeAll(searchLabel, searchRow);
        idField.setEditable(true);
        numberField.setEditable(false);
        ownerField.setEditable(false);
        balanceField.setEditable(false);

        loadButton.setText("Buscar");
        loadButton.setOnAction(event -> loadAccount());
        actionButton.setText("Excluir");
        actionButton.setDisable(true);
        actionButton.setOnAction(event -> deleteAccount());
        cancelButton.setOnAction(event -> closeWindow());
    }

    private void loadAccount() {
        Integer id;
        try {
            id = Integer.valueOf(idField.getText().trim());
        } catch (NumberFormatException exception) {
            showAlert(Alert.AlertType.WARNING, "Código inválido",
                    "Informe um código de conta válido.");
            return;
        }

        try {
            loadedAccount = new AccountDAO(
                    new Database(new DatabaseConfig())
            ).findAccountById(id);

            if (loadedAccount == null) {
                loadedAccount = null;
                numberField.clear();
                ownerField.clear();
                balanceField.clear();
                actionButton.setDisable(true);
                showAlert(Alert.AlertType.INFORMATION, "Conta não encontrada",
                        "Não existe conta com o código informado.");
                return;
            }

            numberField.setText(loadedAccount.getNumber());
            ownerField.setText(loadedAccount.getOwner());
            balanceField.setText(loadedAccount.getBalance().toPlainString());
            actionButton.setDisable(false);
        } catch (SQLException exception) {
            showAlert(Alert.AlertType.ERROR, "Erro de banco de dados",
                    "Não foi possível localizar a conta.");
        }
    }

    private void deleteAccount() {
        Alert confirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Deseja realmente excluir a conta código " + loadedAccount.getAccountId() + "?",
                ButtonType.YES,
                ButtonType.NO
        );
        confirmation.setTitle("Confirmar exclusão");
        confirmation.setHeaderText(null);

        if (confirmation.showAndWait().orElse(ButtonType.NO) != ButtonType.YES) {
            return;
        }

        try {
            boolean deleted = new AccountDAO(
                    new Database(new DatabaseConfig())
            ).deleteAccount(loadedAccount);

            if (deleted) {
                showAlert(Alert.AlertType.INFORMATION, "Exclusão realizada",
                        "Conta excluída com sucesso.");
                closeWindow();
            } else {
                showAlert(Alert.AlertType.ERROR, "Exclusão não realizada",
                        "A conta não pôde ser excluída.");
            }
        } catch (SQLException exception) {
            showAlert(Alert.AlertType.ERROR, "Erro de banco de dados",
                    "Não foi possível excluir a conta.");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) actionButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
