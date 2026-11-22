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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Account;
import model.AccountType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import utils.AccountUtils;

import static utils.AccountAlert.showAlert;

public class AccountDeleteController implements Initializable {
    @FXML
    private GridPane formGrid;
    @FXML
    private Label idLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label ownerLabel;
    @FXML
    private Label balanceLabel;
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
        idLabel.setText("Conta:");
        idField.setEditable(true);

        numberLabel.setManaged(false);
        numberField.setManaged(false);

        loadButton.setText("Buscar");
        loadButton.setOnAction(event -> loadAccount());

        numberField.setEditable(false);
        ownerField.setEditable(false);
        balanceField.setEditable(false);

        typeComboBox.getItems().setAll(AccountType.CHECKING, AccountType.SAVINGS);
        typeComboBox.setValue(AccountType.CHECKING);
        typeComboBox.setDisable(true);

        specificField.setEditable(false);
        specificField.setDisable(true);

        actionButton.setText("Excluir");
        actionButton.setDisable(true);
        actionButton.setOnAction(event -> deleteAccount());

        cancelButton.setOnAction(event -> closeWindow());
    }

    /**
     * Loads the account details based on the account number entered in the idField.
     * <p>
     * This method retrieves the account information from the database using the AccountDAO. If the account is found, it populates the form fields with the account details. If not, it clears the fields and shows an alert indicating that the account was not found.
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
                numberField.clear();
                ownerField.clear();
                balanceField.clear();
                typeComboBox.setValue(AccountType.CHECKING);
                specificField.clear();
                actionButton.setDisable(true);
                showAlert(Alert.AlertType.INFORMATION, "Conta não encontrada",
                        "Não existe conta com o código informado.");
                return;
            }

            numberField.setText(loadedAccount.getNumber());
            ownerField.setText(loadedAccount.getOwner());
            balanceField.setText(loadedAccount.getBalance().toPlainString());
            typeComboBox.setValue(loadedAccount.getAccountType());
            fillSpecificData(loadedAccount);
            actionButton.setDisable(false);
        } catch (SQLException exception) {
            showAlert(Alert.AlertType.ERROR, "Erro de banco de dados",
                    "Não foi possível localizar a conta.");
        }
    }

    private void deleteAccount() {
        if (loadedAccount.getBalance().compareTo(java.math.BigDecimal.ZERO) != 0) {
            showAlert(Alert.AlertType.WARNING, "Exclusão não permitida",
                    "A conta só pode ser excluída com saldo igual a zero.");
            return;
        }
        Alert confirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Deseja realmente excluir a conta número " + loadedAccount.getNumber() + "?",
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
            ).deleteAccountByNumber(loadedAccount.getNumber());

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

    /**
     * Fills the specific data field based on the account type.
     * <p>
     * This method retrieves and displays the specific data (limit for checking accounts or interest rate for savings accounts) in the specificField based on the loaded account's type.
     * </p>
     *
     * @param account The account whose specific data is to be displayed.
     * @throws SQLException If there is an error accessing the database.
     */
    private void fillSpecificData(Account account) throws SQLException {
        AccountUtils.fillSpecificData(account, specificLabel, specificField);
    }

    private void closeWindow() {
        AccountUtils.closeWindows(actionButton);
    }
}
