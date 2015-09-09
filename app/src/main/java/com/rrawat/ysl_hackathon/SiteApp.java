package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 26-08-2015.
 */
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URISyntaxException;

public class SiteApp extends AsyncTask<String,Void,Void>{
    public static Providers allSites;
    private static final String fqcn = SiteApp.class.getName();

    public static void searchProvider(String searchString) throws IOException,
            URISyntaxException {
        String mn = "searchSite(searchString " + searchString + " )";
        System.out.println(fqcn + " :: " + mn);
        String searchProviderURL = LoginApp.BASE_URL + LoginApp.cobName + "/providers/v1?name="
                + searchString;
        String jsonResponse = HTTP.doGet(searchProviderURL, LoginApp.loginTokens);
        Providers providers = (Providers) GSONParser.handleJson(
                jsonResponse, com.rrawat.ysl_hackathon.Providers.class);
        System.out.println(providers.toString());
        allSites = providers;
    }

    public static Providers getProviderLoginForm(String providerId) throws IOException,
            URISyntaxException {
        String mn = "getProviderLoginForm(providerId " + providerId + " )";
        System.out.println(fqcn + " :: " + mn);
        String getSiteURL = LoginApp.BASE_URL + LoginApp.cobName + "/providers/v1/" + providerId;
        String jsonResponse = HTTP.doGet(getSiteURL, LoginApp.loginTokens);
        Providers providers = (Providers) GSONParser.handleJson(
                jsonResponse, com.rrawat.ysl_hackathon.Providers.class);
        System.out.println(providers.toString());
        return providers;
    }


    @Override
    public Void doInBackground(String... params) {


        try {
            searchProvider(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}