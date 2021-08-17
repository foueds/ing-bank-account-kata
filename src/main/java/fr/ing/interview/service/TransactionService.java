package fr.ing.interview.service;

import fr.ing.interview.model.Account;
import fr.ing.interview.model.Customer;
import fr.ing.interview.model.TransactionStatus;
import java.math.BigDecimal;

public class TransactionService {

    public static final BigDecimal MINIMUM_DEPOSIT_ALLOWED = BigDecimal.valueOf(0.01);
    public static final String DELIMITER = ";";
    public static final String USER_ACCOUNT_BALANCE_HEADER = "User" + DELIMITER + "Num Account" + DELIMITER + "Balance";


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

    public void withdrawAccount(Customer customer, BigDecimal amount) {
        Account account = customer.getAccount();
        BigDecimal newBalance = account.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.valueOf(0)) > 0) {
            account.setBalance(newBalance);
            setOperationStatus(TransactionStatus.ACCEPTED);
        } else {
            setOperationStatus(TransactionStatus.REFUSED);
        }
    }

    public void displayCurrentAccountBalance(Customer customer) {
        StringBuilder customerAccountBalance = new StringBuilder(USER_ACCOUNT_BALANCE_HEADER);
        customerAccountBalance.append("\n");
        customerAccountBalance.append(customer.getName())
              .append(DELIMITER)
              .append(customer.getAccount().getId())
              .append(DELIMITER)
              .append(customer.getAccount().getBalance());
        customer.getAccount().setDetailedBalance(customerAccountBalance.toString());
    }


    public TransactionStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(TransactionStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

}
