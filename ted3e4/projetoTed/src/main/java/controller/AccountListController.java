package controller;

import config.DatabaseConfig;
import dao.AccountDAO;
import dao.CheckingAccountDAO;
import dao.SavingsAccountDAO;
import database.Database;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Account;
import model.AccountListItem;
import utils.AccountUtils;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AccountListController implements Initializable {
    @FXML
    private TextField searchField;
    @FXML
    private TableView<AccountListItem> accountTable;
    @FXML
    private TableColumn<AccountListItem, Integer> accountIdColumn;
    @FXML
    private TableColumn<AccountListItem, String> numberColumn;
    @FXML
    private TableColumn<AccountListItem, String> ownerColumn;
    @FXML
    private TableColumn<AccountListItem, BigDecimal> balanceColumn;
    @FXML
    private TableColumn<AccountListItem, String> accountTypeColumn;
    @FXML
    private TableColumn<AccountListItem, BigDecimal> limitColumn;
    @FXML
    private TableColumn<AccountListItem, BigDecimal> yieldRateColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTable();
        searchField.setOnAction(event -> onSearch());
        loadAccounts(null);
    }

    private void configureTable() {
        accountIdColumn.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().accountId()));
        numberColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().number()));
        ownerColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().owner()));
        balanceColumn.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().balance()));
        accountTypeColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().accountType()));
        limitColumn.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().limit()));
        yieldRateColumn.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().yieldRate()));
        accountTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    @FXML
    private void onSearch() {
        loadAccounts(searchField.getText().trim());
    }

    @FXML
    private void onClearSearch() {
        searchField.clear();
        loadAccounts(null);
    }

    @FXML
    private void onClose() {
        closeWindow();
    }

    private void loadAccounts(String term) {
        try {
            Database database = new Database(new DatabaseConfig());
            AccountDAO accountDAO = new AccountDAO(database);
            List<Account> accounts = term == null || term.isEmpty()
                    ? accountDAO.listAccount()
                    : accountDAO.findAccountByTerm(term);
            CheckingAccountDAO checkingDAO = new CheckingAccountDAO(database);
            SavingsAccountDAO savingsDAO = new SavingsAccountDAO(database);

            java.util.ArrayList<AccountListItem> items = new java.util.ArrayList<>();
            for (Account account : accounts) {
                BigDecimal limit = null;
                BigDecimal yieldRate = null;
                if (account.getAccountType().name().equals("CHECKING")) {
                    var checking = checkingDAO.findCheckingAccountById(account.getAccountId());
                    limit = checking == null ? null : checking.getLimit();
                } else {
                    var savings = savingsDAO.findSavingsAccountById(account.getAccountId());
                    yieldRate = savings == null ? null : savings.getYieldRate();
                }
                items.add(new AccountListItem(
                        account.getAccountId(),
                        account.getNumber(),
                        account.getOwner(),
                        account.getBalance(),
                        account.getAccountType().getDescription(),
                        limit,
                        yieldRate
                ));
            }
            accountTable.setItems(FXCollections.observableArrayList(items));
        } catch (SQLException exception) {
            showAlert("Erro de banco de dados",
                    "Não foi possível listar as contas.");
        }
    }

    private void closeWindow() {
        AccountUtils.closeWindows(accountTable);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
