package com.rrawat.ysl_hackathon;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by RRawat on 02-09-2015.
 */
public class BillsApp extends AsyncTask<String,Void,Void>
{
    private static final String fqcn = BillsApp.class.getName();
    public Bills bills;

    public static Bills getBills() throws IOException,
            URISyntaxException {
        String mn = "getBills()";
        System.out.println(fqcn + " :: " + mn);
        String BillsURL = LoginApp.BASE_URL + LoginApp.cobName + "/bills/v1/";
        String jsonResponse = HTTP.doGet(BillsURL,
                LoginApp.loginTokens);
        System.out.println(jsonResponse);
        Bills bills =(Bills) GSONParser.handleJson(
                jsonResponse, com.rrawat.ysl_hackathon.Bills.class);
        return bills;
    }


    @Override
    protected Void doInBackground(String... params) {
        try {
            bills = getBills();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}