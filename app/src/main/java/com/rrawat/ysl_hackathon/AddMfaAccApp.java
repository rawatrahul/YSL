package com.rrawat.ysl_hackathon;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by RRawat on 07-09-2015.
 */
public class AddMfaAccApp extends AsyncTask<String,Void,Void>{


    public static void addMFA_Account(Providers providers) throws IOException, URISyntaxException, InterruptedException
    {
        System.out.println(" Add MFA_Account:  ");
        providers.getProvider()[0].getLoginForm().getRow()[0].getField()[0].setValue("DBmet1.site16442.1");
        providers.getProvider()[0].getLoginForm().getRow()[1].getField()[0].setValue("site16442.1");
        RefreshStatus refreshStatus = AddAccApp.addProviderAccount(providers);
        while(refreshStatus.getLoginForm()==null)
        {
            refreshStatus = AddAccApp.getRefreshStatus(refreshStatus.getProviderAccountId());
        }
        System.out.println("---------------------Escaped-----------------");
        refreshStatus.getLoginForm().getRow()[0].getField()[0].setValue("123456");
        doChallenge(refreshStatus.getLoginForm(), refreshStatus.getProviderAccountId());
        refreshStatus = AddAccApp.getRefreshStatus(refreshStatus.getProviderAccountId());
        refreshStatus.getLoginForm().getRow()[0].getField()[0].setValue("Texas");
        refreshStatus.getLoginForm().getRow()[1].getField()[0].setValue("w3schools");
        doChallenge(refreshStatus.getLoginForm(), refreshStatus.getProviderAccountId());

        // Fetch the accounts if you need !
    }
    public static void doChallenge(LoginForm loginForm, Long providerAccountId)
            throws IOException, URISyntaxException {
        String mn = "doChallenge( " + loginForm.toString() + " providerAccountId = " + providerAccountId;
        Gson gson = new GsonBuilder()
                .create();
        String providerJson = "{\"loginForm\":"+ gson.toJson(loginForm)+"}";
        System.out.println(providerJson);
        String addSiteURL = LoginApp.BASE_URL+ LoginApp.cobName + "/providers/v1/"+providerAccountId ;
        HTTP.doPostUser(addSiteURL, LoginApp.loginTokens,"MFAChallenge="+ providerJson);
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            Providers providers = SiteApp.getProviderLoginForm(params[0]);
            addMFA_Account(providers);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
