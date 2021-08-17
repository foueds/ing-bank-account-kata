package fr.ing.interview.model;

import java.math.BigDecimal;

public class TransactionDetail {

    private Integer id;
    private TransactionType transactionType;
    private BigDecimal transactionAmount;

    public TransactionDetail(Integer id, TransactionType transactionType, BigDecimal transactionAmount) {
        this.id = id;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

}
