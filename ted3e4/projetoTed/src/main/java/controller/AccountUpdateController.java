package controller;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Account;
import model.AccountType;
import dao.CheckingAccountDAO;
import dao.SavingsAccountDAO;
import model.CheckingAccountModel;
import model.SavingsAccount;
import utils.AccountUtils;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static utils.AccountAlert.showAlert;

public class AccountUpdateController implements Initializable {
    @FXML
    private GridPane formGrid;
    @FXML
    private Label ownerLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label idLabel;
    @FXML
    private TextField idField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField ownerField;
    @FXML
    private TextField balanceField;
    @FXML
    private Button loadButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button actionButton;
    @FXML
    private Label typeLabel;
    @FXML
    private ComboBox<AccountType> typeComboBox;
    @FXML
    private Label specificLabel;
    @FXML
    private TextField specificField;

    private Account loadedAccount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idField.setEditable(true);
        idLabel.setText("Conta:");

        numberLabel.setText("Número novo:");
        numberField.setPromptText("Número novo da conta");
        
        typeComboBox.getItems().setAll(AccountType.CHECKING, AccountType.SAVINGS);
        typeComboBox.setValue(AccountType.CHECKING);
        typeComboBox.setDisable(true);
        
        specificField.setEditable(false);
        specificField.setDisable(true);
        
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

    /**
     * Loads the account details based on the provided account ID.
     * <p>
     * This method retrieves the account information from the database using the account ID entered in the idField. If the account is found, it populates the form fields with the account details and enables the action button for updating. If not found, it clears the fields and shows an alert.
     * </p>
     */
    private void loadAccount() {
        if (idField.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Número inválido",
                    "Informe o número da conta.");
            return;
        }

        try {
            loadedAccount = AccountUtils.loadAccount(idField.getText().trim());

            if (loadedAccount == null) {
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
            typeComboBox.setValue(loadedAccount.getAccountType());
            fillSpecificData(loadedAccount);
            actionButton.setDisable(false);
        } catch (SQLException exception) {
            showAlert(Alert.AlertType.ERROR, "Erro de banco de dados",
                    "Não foi possível localizar a conta.");
        }
    }

    /**
     * Updates the account details based on the input fields.
     * <p>
     * This method validates the input fields, updates the loaded account object, and attempts to save the changes to the database. It handles validation errors and database exceptions, providing appropriate alerts to the user.
     * </p>
     */
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
            BigDecimal specific;
            try {
                specific = new BigDecimal(
                        specificField.getText().trim().replace(',', '.')
                );
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

            boolean updated = new AccountDAO(
                    new Database(new DatabaseConfig())
            ).updateAccount(loadedAccount);

            if (updated) {
                Database database = new Database(new DatabaseConfig());
                if (loadedAccount.getAccountType() == AccountType.CHECKING) {
                    new CheckingAccountDAO(database).updateCheckingAccount(
                            new CheckingAccountModel(loadedAccount.getAccountId(), specific)
                    );
                } else {
                    new SavingsAccountDAO(database).updateSavingsAccount(
                            new SavingsAccount(loadedAccount.getAccountId(), specific)
                    );
                }
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

    /**
     * Fills the specific data field based on the account type.
     * <p>
     * This method uses the AccountUtils class to populate the specificLabel and specificField based on whether the account is a checking or savings account. It retrieves the specific data (limit or yield) from the database and displays it in the form.
     * </p>
     *
     * @param account The account object containing the account details.
     * @throws SQLException If there is an error accessing the database.
     */
    private void fillSpecificData(Account account) throws SQLException {
        AccountUtils.fillSpecificData(
                account,
                specificLabel,
                specificField,
                true
        );
    }

    private void clearFields() {
        numberField.clear();
        ownerField.clear();
        balanceField.clear();
        typeComboBox.setValue(AccountType.CHECKING);
        specificField.clear();
        numberField.setDisable(true);
        ownerField.setDisable(true);
        balanceField.setDisable(true);
        actionButton.setDisable(true);
    }

    private void closeWindow() {
        AccountUtils.closeWindows(actionButton);
    }

}
