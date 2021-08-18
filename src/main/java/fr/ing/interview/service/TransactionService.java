package fr.ing.interview.service;

import fr.ing.interview.model.Account;
import fr.ing.interview.model.Customer;
import fr.ing.interview.model.TransactionDetail;
import fr.ing.interview.model.TransactionStatus;
import fr.ing.interview.model.TransactionType;
import java.math.BigDecimal;
import java.util.List;

public class TransactionService {

    public static final BigDecimal MINIMUM_DEPOSIT_ALLOWED = BigDecimal.valueOf(0.01);
    public static final String DELIMITER = ";";
    public static final String USER_ACCOUNT_BALANCE_HEADER = "User" + DELIMITER + "Num Account" + DELIMITER + "Balance";
    public static final String USER_ACCOUNT_TRANSACTION_HISTORY_HEADER =
          "User" + DELIMITER + "Num Account" + DELIMITER + "Transaction Type" + DELIMITER + "Amount";

    private TransactionStatus operationStatus;

    public void depositAccount(Customer customer, BigDecimal amount, Integer transactionId) {
        Account account = customer.getAccount();
        if (amount.compareTo(MINIMUM_DEPOSIT_ALLOWED) > 0) {
            account.setBalance(account.getBalance().add(amount));
            setOperationStatus(TransactionStatus.ACCEPTED);
            TransactionDetail transactionDetail = new TransactionDetail(transactionId, TransactionType.DEPOSIT, amount);
            updateTransactionDetails(account, transactionDetail);
        } else {
            setOperationStatus(TransactionStatus.REFUSED);
        }
    }

    public void withdrawAccount(Customer customer, BigDecimal amount, Integer transactionId) {
        Account account = customer.getAccount();
        BigDecimal newBalance = account.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.valueOf(0)) > 0) {
            account.setBalance(newBalance);
            setOperationStatus(TransactionStatus.ACCEPTED);
            TransactionDetail transactionDetail = new TransactionDetail(transactionId, TransactionType.WITHDRAW, amount);
            updateTransactionDetails(account, transactionDetail);
        } else {
            setOperationStatus(TransactionStatus.REFUSED);
        }
    }

    public void displayCurrentAccountBalance(Customer customer) {
        String customerAccountBalance = USER_ACCOUNT_BALANCE_HEADER + "\n"
              + customer.getName()
              + DELIMITER
              + customer.getAccount().getId()
              + DELIMITER
              + customer.getAccount().getBalance();
        customer.getAccount().setDetailedBalance(customerAccountBalance);
    }

    public String displayAccountTransactionHistory(Customer customer) {
        StringBuilder customerAccountBalance = new StringBuilder(USER_ACCOUNT_TRANSACTION_HISTORY_HEADER + "\n");
        List<TransactionDetail> transactionDetails = customer.getAccount().getTransactionDetails();

        for (TransactionDetail transactionDetail : transactionDetails) {
            customerAccountBalance
                  .append(getTransactionDetail(customer, transactionDetail))
                  .append("\n");
        }
        return customerAccountBalance.toString();
    }

    private void updateTransactionDetails(Account account, TransactionDetail transactionDetail) {
        List<TransactionDetail> transactionDetails = account.getTransactionDetails();
        transactionDetails.add(transactionDetail);
        account.setTransactionDetails(transactionDetails);
    }

    private String getTransactionDetail(Customer customer, TransactionDetail transactionDetail) {
        return customer.getName()
              + DELIMITER
              + customer.getAccount().getId()
              + DELIMITER
              + transactionDetail.getTransactionType()
              + DELIMITER
              + transactionDetail.getTransactionAmount();
    }

    public TransactionStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(TransactionStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

}
