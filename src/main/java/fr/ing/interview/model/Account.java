package fr.ing.interview.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private int id;
    private BigDecimal balance;
    private String detailedBalance;
    private List<TransactionDetail> transactionDetails = new ArrayList<>();

    public Account(int id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDetailedBalance() {
        return detailedBalance;
    }

    public void setDetailedBalance(String detailedBalance) {
        this.detailedBalance = detailedBalance;
    }

    public List<TransactionDetail> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }
}
