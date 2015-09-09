package com.rrawat.ysl_hackathon;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by RRawat on 03-09-2015.
 */
public class AddAccApp extends AsyncTask<String,Void,Void> {
    public static RefreshStatus refreshStatus;
    private static final String fqcn = AddAccApp.class.getName();
    public static RefreshStatus addProviderAccount(Providers providers)
            throws IOException, URISyntaxException {
        String mn = "addProviderAccount( " + providers.getProvider()[0].toString();
        System.out.println(fqcn + " :: " + mn);
        Gson gson = new GsonBuilder()
                .create();
        String providerJson = "{\"provider\":["+ gson.toJson(providers.getProvider()[0])+"]}";
        System.out.println(providerJson);
        String addSiteURL = LoginApp.BASE_URL + LoginApp.cobName + "/providers/v1/" + providers.getProvider()[0].getId();
        String jsonResponse = HTTP.doPut(addSiteURL, providerJson,
                LoginApp.loginTokens);
        RefreshStatus refreshStatus = (RefreshStatus) GSONParser.handleJson(
                jsonResponse, com.rrawat.ysl_hackathon.RefreshStatus.class);
        System.out.println(refreshStatus.toString());
        return refreshStatus;
    }


    public static RefreshStatus getRefreshStatus(Long providerAccountId)
            throws IOException, URISyntaxException {
        String mn = "getRefreshStatus( " + providerAccountId.toString() + " )";
        System.out.println(fqcn + " :: " + mn);
        String getRefreshStatusURL = LoginApp.BASE_URL + LoginApp.cobName + "/refresh/v1/"
                + providerAccountId.toString();
        String jsonResponse =  HTTP.doGet(getRefreshStatusURL, LoginApp.loginTokens);
        RefreshStatus refreshStatus = (RefreshStatus) GSONParser.handleJson(
                jsonResponse, com.rrawat.ysl_hackathon.RefreshStatus.class);
        System.out.println(refreshStatus.toString());
        return refreshStatus;
    }

    public static void getRefreshStatus(RefreshStatus refreshStatus) throws IOException, URISyntaxException
    {
        System.out.println(refreshStatus.toString());
        //Keep Polling this api to get RefreshStatus :
        for(int i=0; i<10; i++)
        {
            getRefreshStatus(refreshStatus.getProviderAccountId());
        }
    }

    @Override
    protected Void doInBackground(String... params) {
        try {

            Providers loginForm = SiteApp.getProviderLoginForm(params[0]);
            loginForm.getProvider()[0].getLoginForm().getRow()[0].getField()[0].setValue(params[1]);
            loginForm.getProvider()[0].getLoginForm().getRow()[1].getField()[0].setValue(params[2]);
            System.out.println(loginForm.getProvider()[0].getLoginForm().getRow()[0].getField()[0].getValue() + " " + loginForm.getProvider()[0].getLoginForm().getRow()[1].getField()[0].getValue());
            refreshStatus = AddAccApp.addProviderAccount(loginForm);
            while (!refreshStatus.getRefreshInfo().getRefreshStatus().equalsIgnoreCase("REFRESH_COMPLETED")){
                refreshStatus = AddAccApp.getRefreshStatus(refreshStatus.getProviderAccountId());
            System.out.println(refreshStatus.getRefreshInfo().getRefreshStatus()+" - "+refreshStatus.getRefreshInfo().getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
