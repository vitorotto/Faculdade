package controller;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
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

public class AccountInsertController implements Initializable {
    @FXML private GridPane formGrid;
    @FXML private Label searchLabel;
    @FXML private HBox searchRow;
    @FXML private Label idLabel;
    @FXML private HBox idRow;
    @FXML private Label numberLabel;
    @FXML private Label ownerLabel;
    @FXML private Label balanceLabel;
    @FXML private Button searchButton;
    @FXML private Button loadButton;
    @FXML private Button cancelButton;
    @FXML private Button actionButton;

    @FXML
    private TextField numberField;

    @FXML
    private TextField ownerField;

    @FXML
    private TextField balanceField;

    @FXML
    private Button saveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formGrid.getChildren().removeAll(
                searchLabel, searchRow, idLabel, idRow
        );
        GridPane.setRowIndex(numberLabel, 0);
        GridPane.setRowIndex(numberField, 0);
        GridPane.setRowIndex(ownerLabel, 1);
        GridPane.setRowIndex(ownerField, 1);
        GridPane.setRowIndex(balanceLabel, 2);
        GridPane.setRowIndex(balanceField, 2);
        actionButton.setManaged(true);
        actionButton.setVisible(true);
        cancelButton.setManaged(true);
        cancelButton.setVisible(true);
        GridPane.setRowIndex(actionButton.getParent(), 3);
        numberLabel.setText("Número:");
        ownerLabel.setText("Titular:");
        balanceLabel.setText("Saldo inicial:");
        balanceField.setPromptText("0,00");
        actionButton.setText("Salvar");
        actionButton.setOnAction(this::onSalvar);
        cancelButton.setOnAction(this::onCancelar);
        saveButton = actionButton;

        numberField.requestFocus();
    }

    @FXML
    private void onSalvar(ActionEvent event) {
        String number = numberField.getText().trim();
        String owner = ownerField.getText().trim();
        String balanceText = balanceField.getText().trim().replace(',', '.');

        if (number.isEmpty() || owner.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Dados incompletos",
                    "Informe o número da conta e o titular.");
            return;
        }

        if (number.length() > 20) {
            showAlert(Alert.AlertType.WARNING, "Número inválido",
                    "O número da conta deve ter no máximo 20 caracteres.");
            return;
        }

        if (owner.length() > 100) {
            showAlert(Alert.AlertType.WARNING, "Titular inválido",
                    "O titular deve ter no máximo 100 caracteres.");
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
                    "O saldo inicial não pode ser negativo.");
            return;
        }

        try {
            Database database = new Database(new DatabaseConfig());
            AccountDAO accountDAO = new AccountDAO(database);
            Account account = accountDAO.insertAccount(
                    new Account(number, owner, balance)
            );

            showAlert(Alert.AlertType.INFORMATION, "Inclusão realizada",
                    "Conta incluída com sucesso.\nCódigo gerado: " + account.getAccountId());

            closeWindow();
        } catch (SQLException exception) {
            if ("23000".equals(exception.getSQLState())) {
                showAlert(Alert.AlertType.ERROR, "Cadastro não realizado",
                        "Já existe uma conta com este número.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro de banco de dados",
                        "Não foi possível salvar a conta. Verifique a conexão e tente novamente.");
            }
        }
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
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
