package com.rrawat.ysl_hackathon;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by RRawat on 10-06-2015.
 */
public class LoginApp extends AsyncTask<String,Void,Void>{

    public final static String BASE_URL = "https://stage.api.yodlee.com/ysl/";
    public static String cobName = "private-sandboxtwo";
//    public final static String BASE_URL = "http://192.168.57.9:8980/ysl/";
//    public static String cobName = "yodlee";

    private static final String fqcn = LoginApp.class.getName();
    public static Map<String, String> loginTokens = new HashMap<String, String>();

    public static void doCoBrandLogin(String coBrandUserName, String coBrandPassword) throws IOException {
        String mn = "doCoBrandLogin(coBrandUserName " + coBrandUserName + ", coBrandPassword " + coBrandPassword + " )";
        String coBrandLoginURL = BASE_URL + cobName + "/cobrand/v1/login";
        final String requestBody = "cobrandLogin=" + coBrandUserName + "&cobrandPassword=" + coBrandPassword;
        String jsonResponse = HTTP.doPost(coBrandLoginURL, requestBody);
        CobrandContext coBrand = (CobrandContext) GSONParser.handleJson(jsonResponse, com.rrawat.ysl_hackathon.CobrandContext.class);
        Log.d("rahul", coBrand.toString());
        loginTokens.put("cobSession", coBrand.getSession().getCobSession());

    }

    public static void doMemberLogin(String userName, String userPassword) throws IOException
    {
        String mn = "doMemberLogin(userLogin=" +userName+ ", userPassword = " + userPassword + ", coBrandSessionCredential =" + loginTokens.get("cobSession") + " )";
        Log.d("rahul", fqcn + " :: " + mn);
        final String requestBody="coBrandSessionCredential="+ loginTokens.get("cobSession")+"&userLogin=" + userName + "&userPassword="+ userPassword;
        String userLoginURL = BASE_URL + cobName +  "/user/v1/login";
        //HTTP.addHeaders("Authorization" , loginTokens.get("cobSession"));
        String jsonResponse = HTTP.doPostUser(userLoginURL, loginTokens, requestBody);
        UserContext member = (UserContext) GSONParser.handleJson(jsonResponse, com.rrawat.ysl_hackathon.UserContext.class);
        Log.d("rahul", member.getSession().getUserSession());
        loginTokens.put("userSession", member.getSession().getUserSession());
    }

    public static void doLogin(String coBrandUserName, String coBrandPassword,String userName, String userPassword) throws IOException
    {
        doCoBrandLogin(coBrandUserName, coBrandPassword);
        doMemberLogin(userName, userPassword);
    }

    @Override
    public Void doInBackground(String... params) {
        String cobName = params[0];
        String cobPass = params[1];
        String userName = params[2];
        String userPass = params[3];

        try {
            doLogin(cobName,cobPass,userName,userPass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}