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
            transactions.append(transaction[i].getId()).append("=>").append("amount=").append(transaction[i].getAmount().getAmount() + " "+ transaction[i].getAmount().getCurrency()).append(" => ").append("date=").append(transaction[i].getDate().getDate() + transaction[i].getDate().getFormat()).append("=>" + transaction[i].getBaseType()).append("\n");
        }
        return transactions.toString();
    }


    public String datatoString()
    {
        StringBuilder transactions = new StringBuilder("");
        for (int i = 0; i<transaction.length; i++)
        {
            transactions.append(transaction[i].getDate().getDate()).append(" - ").append(transaction[i].getOriginalDescription()).append(" - "+ transaction[i].getAmount().getAmount()).append(" - " + transaction[i].getCategory()).append("\n");
        }
        return transactions.toString();
    }
}
