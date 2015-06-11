package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 11-06-2015.
 */
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URISyntaxException;


public class TransactionApp extends AsyncTask<String, Void,Void>{

    private static final String fqcn = TransactionApp.class.getName();

    public static Transactions getTransactions() throws IOException, URISyntaxException {
        String mn = "getTransactions()";
        System.out.println(fqcn + " :: " + mn);
        String transactionsURL = LoginApp.BASE_URL + LoginApp.cobName + "transactions/v1/";
        String jsonResponse = HTTPS.doGet(transactionsURL,
                LoginApp.loginTokens);
        System.out.println(jsonResponse);
        Transactions transactions = (Transactions) GSONParser.handleJson(jsonResponse, com.rrawat.ysl_hackathon.Transactions.class);
        return transactions;
    }

    @Override
    protected Void doInBackground(String... params) {
        return null;
    }
}
