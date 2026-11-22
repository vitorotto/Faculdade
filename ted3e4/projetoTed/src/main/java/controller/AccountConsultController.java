package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Account;
import model.AccountType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static utils.AccountAlert.showAlert;
import utils.AccountUtils;

public class AccountConsultController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idLabel.setText("Conta:");
        idField.setEditable(true);

        numberLabel.setManaged(false);
        numberField.setManaged(false);

        ownerField.setEditable(false);
        ownerField.setDisable(true);

        balanceField.setEditable(false);
        balanceField.setDisable(true);

        typeComboBox.getItems().setAll(AccountType.CHECKING, AccountType.SAVINGS);
        typeComboBox.setValue(AccountType.CHECKING);
        typeComboBox.setDisable(true);

        specificField.setEditable(false);
        specificField.setDisable(true);

        loadButton.setText("Buscar");
        loadButton.setOnAction(event -> consultAccount());

        actionButton.setText("Fechar");
        actionButton.setOnAction(event -> AccountUtils.closeWindows(actionButton));
        cancelButton.setOnAction(event -> AccountUtils.closeWindows(cancelButton));
    }

    /**
     * Loads the account details based on the account number entered in the idField.
     * <p>
     * This method retrieves the account information from the database using the AccountDAO. If the account is found, it populates the form fields with the account details. If not, it clears the fields and shows an alert indicating that the account was not found.
     * </p>
     */
    private void consultAccount() {
        if (idField.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Número inválido",
                    "Informe o número da conta.");
            return;
        }

        try {
            Account account = AccountUtils.loadAccount(idField.getText().trim());

            if (account == null) {
                numberField.clear();
                ownerField.clear();
                balanceField.clear();
                numberField.setDisable(true);
                ownerField.setDisable(true);
                balanceField.setDisable(true);
                specificField.clear();
                specificField.setDisable(true);
                showAlert(Alert.AlertType.INFORMATION, "Conta não encontrada",
                        "Não existe conta com o número informado.");
                return;
            }

            numberField.setText(account.getNumber());
            ownerField.setText(account.getOwner());
            balanceField.setText(account.getBalance().toPlainString());
            numberField.setDisable(false);
            ownerField.setDisable(false);
            balanceField.setDisable(false);
            typeComboBox.setValue(account.getAccountType());
            AccountUtils.fillSpecificData(account, specificLabel, specificField);
        } catch (SQLException exception) {
            showAlert(Alert.AlertType.ERROR, "Erro de banco de dados",
                    "Não foi possível localizar a conta.");
        }
    }
}
