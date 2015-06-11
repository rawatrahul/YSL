package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 11-06-2015.
 */
public class Accounts {

    Account[] account;

    public Account[] getAccounts() {
        return account;
    }

    public void setAccounts(Account[] account) {
        this.account = account;
    }

    public String toString()
    {
        StringBuilder accounts = new StringBuilder("");
        for (int i = 0; i<account.length; i++)
        {
            accounts.append(account[i].getId()).append("=>").append(account[i].getAccountName()).append("\n");
        }
        return accounts.toString();
    }

}
