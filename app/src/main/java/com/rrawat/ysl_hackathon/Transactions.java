package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 11-06-2015.
 */
public class Transactions {

    Transaction[] transaction;

    public Transaction[] getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction[] transaction) {
        this.transaction = transaction;
    }

    public String toString()
    {
        StringBuilder transactions = new StringBuilder("");
        for (int i = 0; i<transaction.length; i++)
        {
            transactions.append(transaction[i].getId()).append("=>").append("amount=").append(transaction[i].getAmount()).append("=>" + transaction[i].getBaseType()).append("\n");
        }
        return transactions.toString();
    }

    public String datatoString()
    {
        StringBuilder transactions = new StringBuilder("");
        for (int i = 0; i<transaction.length; i++)
        {
            transactions.append(transaction[i].getDate()).append(" - ").append(transaction[i].getDescription()).append(" - "+ transaction[i].getAmount()).append(" - " + transaction[i].getBaseType()).append("\n");
        }
        return transactions.toString();
    }
}
