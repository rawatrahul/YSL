package com.rrawat.ysl_hackathon;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by RRawat on 02-09-2015.
 */
public class HoldingApp extends AsyncTask<String,Void,Void> {

    private static final String fqcn = HoldingApp.class.getName();
    public Holdings allHoldings;

    public static Holdings getHoldings() throws IOException,
            URISyntaxException {
        String mn = "getHoldings()";
        System.out.println(fqcn + " :: " + mn);
        String holdingsURL = LoginApp.BASE_URL+ LoginApp.cobName + "/holdings/v1/";
        String jsonResponse = HTTP.doGet(holdingsURL,
                LoginApp.loginTokens);
        System.out.println(jsonResponse);
        Holdings holdings =(Holdings) GSONParser.handleJson(
                jsonResponse, com.rrawat.ysl_hackathon.Holdings.class);
        return holdings;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            allHoldings = getHoldings();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
