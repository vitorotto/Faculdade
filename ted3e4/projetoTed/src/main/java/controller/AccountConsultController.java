package controller;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Account;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountConsultController implements Initializable {
    @FXML private GridPane formGrid;
    @FXML private Label idLabel;
    @FXML private HBox idRow;
    @FXML private Label numberLabel;
    @FXML private Label ownerLabel;
    @FXML private Label balanceLabel;
    @FXML private TextField idField;
    @FXML private TextField numberField;
    @FXML private TextField ownerField;
    @FXML private TextField balanceField;
    @FXML private Label searchLabel;
    @FXML private HBox searchRow;
    @FXML private Button loadButton;
    @FXML private Button cancelButton;
    @FXML private Button actionButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formGrid.getChildren().removeAll(searchLabel, searchRow);

        idField.setEditable(true);
        numberField.setEditable(false);
        ownerField.setEditable(false);
        balanceField.setEditable(false);
        numberField.setDisable(true);
        ownerField.setDisable(true);
        balanceField.setDisable(true);

        loadButton.setText("Buscar");
        loadButton.setOnAction(event -> loadAccount());
        actionButton.setText("Fechar");
        actionButton.setOnAction(event -> closeWindow());
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
            Account account = new AccountDAO(
                    new Database(new DatabaseConfig())
            ).findAccountById(id);

            if (account == null) {
                numberField.clear();
                ownerField.clear();
                balanceField.clear();
                numberField.setDisable(true);
                ownerField.setDisable(true);
                balanceField.setDisable(true);
                showAlert(Alert.AlertType.INFORMATION, "Conta não encontrada",
                        "Não existe conta com o código informado.");
                return;
            }

            numberField.setText(account.getNumber());
            ownerField.setText(account.getOwner());
            balanceField.setText(account.getBalance().toPlainString());
            numberField.setDisable(false);
            ownerField.setDisable(false);
            balanceField.setDisable(false);
        } catch (SQLException exception) {
            showAlert(Alert.AlertType.ERROR, "Erro de banco de dados",
                    "Não foi possível localizar a conta.");
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
