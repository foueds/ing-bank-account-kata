package fr.ing.interview.service;

import fr.ing.interview.model.Account;
import fr.ing.interview.model.Customer;
import fr.ing.interview.model.TransactionStatus;
import java.math.BigDecimal;

public class TransactionService {

    public static final BigDecimal MINIMUM_DEPOSIT_ALLOWED = BigDecimal.valueOf(0.01);

    private TransactionStatus operationStatus;

    public void depositAccount(Customer customer, BigDecimal amount) {
        Account account = customer.getAccount();
        if (amount.compareTo(MINIMUM_DEPOSIT_ALLOWED) > 0) {
            account.setBalance(account.getBalance().add(amount));
            setOperationStatus(TransactionStatus.ACCEPTED);
        } else {
            setOperationStatus(TransactionStatus.REFUSED);
        }
    }

    public TransactionStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(TransactionStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

}
