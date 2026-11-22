package controller;

import config.DatabaseConfig;
import dao.AccountDAO;
import dao.OperationDAO;
import database.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Account;
import model.Operation;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static utils.AccountUtils.closeWindows;

public class AccountOperationController implements Initializable {
    public enum OperationType {
        DEPOSIT,
        WITHDRAW,
        STATEMENT
    }

    @FXML private TextField numberField;
    @FXML private TextField valueField;
    @FXML private TextArea resultArea;
    @FXML private Label valueLabel;
    @FXML private Button actionButton;
    @FXML private Button cancelButton;

    private final OperationType operationType;

    public AccountOperationController(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (operationType == OperationType.DEPOSIT) {
            resultArea.setVisible(false);
            resultArea.setManaged(false);
            actionButton.setText("Depositar");
        } else if (operationType == OperationType.WITHDRAW) {
            resultArea.setVisible(false);
            resultArea.setManaged(false);
            actionButton.setText("Sacar");
        } else {
            valueLabel.setVisible(false);
            valueLabel.setManaged(false);
            valueField.setVisible(false);
            valueField.setManaged(false);
            actionButton.setText("Consultar extrato");
        }

        actionButton.setOnAction(event -> onAction());
        cancelButton.setOnAction(event -> onCancel());

    }

    @FXML
    private void onAction() {
        String number = numberField.getText().trim();
        if (number.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Número obrigatório",
                    "Informe o número da conta.");
            return;
        }

        try {
            OperationDAO operationDAO = new OperationDAO(
                    new Database(new DatabaseConfig())
            );

            if (operationType == OperationType.STATEMENT) {
                List<Operation> operations = operationDAO.listOperations(number);
                if (operations.isEmpty()) {
                    resultArea.setText("Nenhuma operação encontrada.");
                } else {
                    StringBuilder text = new StringBuilder();
                    for (Operation operation : operations) {
                        text.append(operation.getDate()).append(" - ")
                                .append(operation.getOperation()).append(" - R$ ")
                                .append(operation.getValue()).append('\n');
                    }
                    resultArea.setText(text.toString());
                }
                return;
            }

            BigDecimal value;
            try {
                value = new BigDecimal(valueField.getText().trim().replace(',', '.'));
            } catch (NumberFormatException exception) {
                showAlert(Alert.AlertType.WARNING, "Valor inválido",
                        "Informe um valor válido.");
                return;
            }

            boolean success = operationType == OperationType.DEPOSIT
                    ? operationDAO.deposit(number, value)
                    : operationDAO.withdraw(number, value);

            if (success) {
                Account account = new AccountDAO(
                        new Database(new DatabaseConfig())
                ).findAccountByNumber(number);
                showAlert(Alert.AlertType.INFORMATION, "Operação realizada",
                        "Saldo atualizado: R$ " + account.getBalance());
            } else {
                showAlert(Alert.AlertType.WARNING, "Operação não realizada",
                        "Conta inexistente, valor inválido ou saldo/limite insuficiente.");
            }
        } catch (SQLException exception) {
            showAlert(Alert.AlertType.ERROR, "Erro de banco de dados",
                    "Não foi possível realizar a operação.");
        }
    }

    @FXML
    private void onCancel() {
        closeWindows(numberField);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
