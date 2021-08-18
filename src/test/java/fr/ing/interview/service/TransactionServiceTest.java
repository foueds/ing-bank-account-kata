package fr.ing.interview.service;

import static fr.ing.interview.service.TransactionService.DELIMITER;
import static fr.ing.interview.service.TransactionService.USER_ACCOUNT_TRANSACTION_HISTORY_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.ing.interview.model.Account;
import fr.ing.interview.model.Customer;
import fr.ing.interview.model.TransactionStatus;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionServiceTest {

    private Customer customer;
    private TransactionService operationCustomer;


    @BeforeEach
    void setUp() {
        Account accountCustomer = new Account(1, BigDecimal.valueOf(100));
        customer = new Customer(1, "John", accountCustomer);
        operationCustomer = new TransactionService();
    }

    @Test
    void should_accept_deposit_money_from_account_when_amount_superior_to_minimum_deposit_allowed() {
        //WHEN
        operationCustomer.depositAccount(customer, BigDecimal.valueOf(1200), 1);

        //THEN
        assertEquals(TransactionStatus.ACCEPTED, operationCustomer.getOperationStatus());
        assertEquals(BigDecimal.valueOf(1300), customer.getAccount().getBalance());
    }

    @Test
    void should_refuse_deposit_money_from_account_when_amount_superior_to_minimum_deposit_allowed() {
        //WHEN
        operationCustomer.depositAccount(customer, BigDecimal.valueOf(0.01), 1);

        //THEN
        assertEquals(TransactionStatus.REFUSED, operationCustomer.getOperationStatus());
        assertEquals(BigDecimal.valueOf(100), customer.getAccount().getBalance());
    }

    @Test
    void should_accept_withdraw_money_from_account_when_no_overdraft_used() {
        //WHEN
        operationCustomer.withdrawAccount(customer, BigDecimal.valueOf(70), 1);

        //THEN
        assertEquals(TransactionStatus.ACCEPTED, operationCustomer.getOperationStatus());
        assertEquals(BigDecimal.valueOf(30), customer.getAccount().getBalance());
    }

    @Test
    void should_refuse_withdraw_money_from_account_when_no_overdraft_used() {
        //WHEN
        operationCustomer.withdrawAccount(customer, BigDecimal.valueOf(300), 1);

        //THEN
        assertEquals(TransactionStatus.REFUSED, operationCustomer.getOperationStatus());
        assertEquals(BigDecimal.valueOf(100), customer.getAccount().getBalance());
    }

    @Test
    void should_display_customer_account_balance() {
        //WHEN
        operationCustomer.displayCurrentAccountBalance(customer);
        String expectedCustomerAccountBalance =
              "User" + DELIMITER + "Num Account" + DELIMITER + "Balance" + "\n" + "John" + DELIMITER + "1" + DELIMITER + "100";

        //THEN
        assertEquals(expectedCustomerAccountBalance, customer.getAccount().getDetailedBalance());
    }

    @Test
    void should_display_customer_account_transaction_history() {

        operationCustomer.depositAccount(customer, BigDecimal.valueOf(1200), 1);
        operationCustomer.withdrawAccount(customer, BigDecimal.valueOf(70), 2);

        String expectedTransactionHistory = USER_ACCOUNT_TRANSACTION_HISTORY_HEADER
              + "\n"
              + "John" + DELIMITER + "1" + DELIMITER + "DEPOSIT"+ DELIMITER + "1200"
              + "\n"
              + "John" + DELIMITER + "1" + DELIMITER + "WITHDRAW"+ DELIMITER + "70"
              + "\n";

        //WHEN
        String transactionHistory = operationCustomer.displayAccountTransactionHistory(customer);

        //THEN
        assertEquals(2, customer.getAccount().getTransactionDetails().size());
        assertEquals(expectedTransactionHistory, transactionHistory);

    }

}
