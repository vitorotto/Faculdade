package test;

import model.Account;

import java.math.BigDecimal;

public class MainAccountCrudTest {
    static void main() {
        IO.println("*== Account CRUD Tests ==*");
        // Insert test
        Account account = new Account("51999991111", "Teste", new BigDecimal("5000.50"));
        InsertAccountTest.executeInsertTest(account);
        Integer currentAccountId = account.getAccountId();
        // FindAccountById test
        FindAccountByIdTest.executeFindAccountByIdTest(currentAccountId);
        // Update test
        account.setBalance(BigDecimal.valueOf(4000.00));
        UpdateAccountTest.executeUpdateTest(account, currentAccountId);
        // Delete test
        DeleteAccountTest.executeDeleteTest(account, currentAccountId);
        IO.println("*== ================== ==*");
    }
}
