package fr.ing.interview.service;

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
        operationCustomer.depositAccount(customer, BigDecimal.valueOf(1200));

        //THEN
        assertEquals(TransactionStatus.ACCEPTED, operationCustomer.getOperationStatus());
        assertEquals(BigDecimal.valueOf(1300), customer.getAccount().getBalance());
    }

    @Test
    void should_refuse_deposit_money_from_account_when_amount_superior_to_minimum_deposit_allowed() {
        //WHEN
        operationCustomer.depositAccount(customer, BigDecimal.valueOf(0.01));

        //THEN
        assertEquals(TransactionStatus.REFUSED, operationCustomer.getOperationStatus());
        assertEquals(BigDecimal.valueOf(100), customer.getAccount().getBalance());
    }

    @Test
    void should_accept_withdraw_money_from_account_when_no_overdraft_used() {
        //WHEN
        operationCustomer.withdrawAccount(customer, BigDecimal.valueOf(70));

        //THEN
        assertEquals(TransactionStatus.ACCEPTED, operationCustomer.getOperationStatus());
        assertEquals(BigDecimal.valueOf(30), customer.getAccount().getBalance());
    }

    @Test
    void should_refuse_withdraw_money_from_account_when_no_overdraft_used() {
        //WHEN
        operationCustomer.withdrawAccount(customer, BigDecimal.valueOf(300));

        //THEN
        assertEquals(TransactionStatus.REFUSED, operationCustomer.getOperationStatus());
        assertEquals(BigDecimal.valueOf(100), customer.getAccount().getBalance());
    }
}
