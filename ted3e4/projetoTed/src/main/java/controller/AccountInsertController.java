package controller;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Account;
import model.AccountType;
import utils.AccountUtils;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static utils.AccountAlert.showAlert;

public class AccountInsertController implements Initializable {
    @FXML
    private GridPane formGrid;
    @FXML
    private Label idLabel;
    @FXML
    private HBox idRow;
    @FXML
    private Label numberLabel;
    @FXML
    private Label ownerLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Button loadButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button actionButton;
    @FXML
    private ComboBox<AccountType> typeComboBox;
    @FXML
    private Label typeLabel;
    @FXML
    private Label specificLabel;
    @FXML
    private TextField specificField;
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
        actionButton.setManaged(true);
        actionButton.setVisible(true);

        cancelButton.setManaged(true);
        cancelButton.setVisible(true);

        numberLabel.setText("Número:");
        numberField.requestFocus();

        ownerLabel.setText("Titular:");

        balanceLabel.setText("Saldo inicial:");
        balanceField.setPromptText("0,00");

        actionButton.setText("Salvar");
        actionButton.setOnAction(this::onSalvar);

        cancelButton.setOnAction(this::onCancelar);

        saveButton = actionButton;

        typeComboBox.getItems().setAll(AccountType.CHECKING, AccountType.SAVINGS);
        typeComboBox.setValue(AccountType.CHECKING);
        typeComboBox.setOnAction(event -> updateSpecificField());
        updateSpecificField();
    }

    /**
     * Updates the specific field label and prompt text based on the selected account type.
     * <p>
     * This method checks the selected account type in the typeComboBox and updates the specificLabel and specificField's prompt text accordingly. For checking accounts, it sets the label to "Limite:" and for savings accounts, it sets it to "Rendimento:".
     * </p>
     */
    private void updateSpecificField() {
        if (typeComboBox.getValue() == AccountType.CHECKING) {
            specificLabel.setText("Limite:");
            specificField.setPromptText("Ex.: 300,00");
        } else {
            specificLabel.setText("Rendimento:");
            specificField.setPromptText("Ex.: 0,50");
        }
    }

    /**
     * Handles the save action when the user clicks the "Salvar" button.
     * <p>
     * This method validates the input fields, creates a new account based on the provided data, and attempts to save it to the database. It displays appropriate alerts for validation errors or database issues.
     * </p>
     *
     * @param event The ActionEvent triggered by clicking the "Salvar" button.
     */
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
            String specificText = specificField.getText().trim().replace(',', '.');
            BigDecimal specific;
            try {
                specific = specificText.isEmpty()
                        ? BigDecimal.ZERO
                        : new BigDecimal(specificText);
            } catch (NumberFormatException exception) {
                showAlert(Alert.AlertType.WARNING, "Valor inválido",
                        "Informe um valor válido para o limite ou rendimento.");
                return;
            }
            if (specific.compareTo(BigDecimal.ZERO) < 0) {
                showAlert(Alert.AlertType.WARNING, "Valor inválido",
                        "Limite ou rendimento não pode ser negativo.");
                return;
            }

            Account account = new Account(number, owner, balance);
            if (typeComboBox.getValue() == AccountType.CHECKING) {
                account = accountDAO.insertCheckingAccount(account, specific);
            } else {
                account = accountDAO.insertSavingsAccount(account, specific);
            }

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
        AccountUtils.closeWindows(saveButton);
    }
}
