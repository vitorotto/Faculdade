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

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountUpdateController implements Initializable {
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
        numberField.setDisable(true);
        ownerField.setDisable(true);
        balanceField.setDisable(true);

        loadButton.setText("Buscar");
        loadButton.setOnAction(event -> loadAccount());
        actionButton.setText("Salvar");
        actionButton.setDisable(true);
        actionButton.setOnAction(event -> updateAccount());
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
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Conta não encontrada",
                        "Não existe conta com o código informado.");
                return;
            }

            numberField.setText(loadedAccount.getNumber());
            ownerField.setText(loadedAccount.getOwner());
            balanceField.setText(loadedAccount.getBalance().toPlainString());
            numberField.setDisable(false);
            ownerField.setDisable(false);
            balanceField.setDisable(false);
            actionButton.setDisable(false);
        } catch (SQLException exception) {
            showAlert(Alert.AlertType.ERROR, "Erro de banco de dados",
                    "Não foi possível localizar a conta.");
        }
    }

    private void updateAccount() {
        String number = numberField.getText().trim();
        String owner = ownerField.getText().trim();
        String balanceText = balanceField.getText().trim().replace(',', '.');

        if (number.isEmpty() || owner.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Dados incompletos",
                    "Informe o número da conta e o titular.");
            return;
        }

        if (number.length() > 20 || owner.length() > 100) {
            showAlert(Alert.AlertType.WARNING, "Dados inválidos",
                    "Número máximo: 20 caracteres. Titular máximo: 100 caracteres.");
            return;
        }

        BigDecimal balance;
        try {
            balance = balanceText.isEmpty()
                    ? BigDecimal.ZERO
                    : new BigDecimal(balanceText);
        } catch (NumberFormatException exception) {
            showAlert(Alert.AlertType.WARNING, "Saldo inválido",
                    "Informe um saldo válido, como 100 ou 100,50.");
            return;
        }

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            showAlert(Alert.AlertType.WARNING, "Saldo inválido",
                    "O saldo não pode ser negativo.");
            return;
        }

        loadedAccount.setNumber(number);
        loadedAccount.setOwner(owner);
        loadedAccount.setBalance(balance);

        try {
            boolean updated = new AccountDAO(
                    new Database(new DatabaseConfig())
            ).updateAccount(loadedAccount);

            if (updated) {
                showAlert(Alert.AlertType.INFORMATION, "Alteração realizada",
                        "Conta alterada com sucesso.");
                closeWindow();
            } else {
                showAlert(Alert.AlertType.ERROR, "Alteração não realizada",
                        "A conta não pôde ser alterada.");
            }
        } catch (SQLException exception) {
            showAlert(Alert.AlertType.ERROR, "Erro de banco de dados",
                    "Não foi possível alterar a conta.");
        }
    }

    private void clearFields() {
        numberField.clear();
        ownerField.clear();
        balanceField.clear();
        numberField.setDisable(true);
        ownerField.setDisable(true);
        balanceField.setDisable(true);
        actionButton.setDisable(true);
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
