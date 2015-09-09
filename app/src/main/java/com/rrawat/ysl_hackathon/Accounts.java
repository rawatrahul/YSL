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
        if(account != null) {
            for (int i = 0; i < account.length; i++) {
                accounts.append(account[i].getAccountNumber()).append("=>").append(account[i].getAccountName()).append("=>").append(account[i].getBalance()).append("\n");
            }
        }
        return accounts.toString();
    }
    public String datatoString()
    {
        StringBuilder accounts = new StringBuilder("");
        if(account != null) {
            for (int i = 0; i < account.length; i++) {
            //accounts.append(account[i].getAccountName()).append(" - ").append(account[i].getId()).append(" - ").append(account[i].getBalance()).append("\n");
                accounts.append(account[i].getAccountName()).append(" - ").append(account[i].getAccountNumber()).append(" - ").append(account[i].getCONTAINER()).append(" - ").append(account[i].getBalance()).append("\n");
            }
        }
        return accounts.toString();
    }

}
