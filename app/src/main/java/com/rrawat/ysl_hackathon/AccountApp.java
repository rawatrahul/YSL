package com.rrawat.ysl_hackathon;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by RRawat on 11-06-2015.
 */
public class AccountApp extends AsyncTask<String, Void, Accounts> {
    private static final String fqcn = AccountApp.class.getName();
    public Accounts allAccounts = null;

    public static Accounts getAccounts() throws IOException,
            URISyntaxException {
        String mn = "getAccounts()";
        System.out.println(fqcn + " :: " + mn);
        String accountSummaryURL = LoginApp.BASE_URL + LoginApp.cobName + "/accounts/v1";
        //String accountSummaryURL = "https://stage.api.yodlee.com:443/ysl/private-yslsandbox20/accounts/v1";
        String jsonResponse = HTTPS.doGet(accountSummaryURL,  LoginApp.loginTokens);
        Log.d("rahul", jsonResponse);
        Accounts accounts =(Accounts) GSONParser.handleJson(
                jsonResponse, com.rrawat.ysl_hackathon.Accounts.class);
        return accounts;
    }

    public static Accounts getAccountsForContainer(String accountSummaryURL) throws IOException,
            URISyntaxException {
        String mn = "getAccounts()";
        System.out.println(fqcn + " :: " + mn);
        String jsonResponse = HTTPS.doGet(accountSummaryURL,  LoginApp.loginTokens);
        Log.d("rahul", jsonResponse);
        Accounts accounts =(Accounts) GSONParser.handleJson(
                jsonResponse, com.rrawat.ysl_hackathon.Accounts.class);
        return accounts;
    }

    @Override
    protected Accounts doInBackground(String... params) {

        try {
            if(null == params ||params.length==0|| params[0]==null)
                allAccounts = getAccounts();
            else
                allAccounts = getAccountsForContainer(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return allAccounts;
    }
}
