package com.rrawat.ysl_hackathon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity implements OnClickListener {

    private static String coBrandUserName = "private-sandboxtwo";
    private static String coBrandPassword = "Yodlee@123";
//    private static String coBrandUserName = "yodlee_10000004";
//    private static String coBrandPassword = "yodlee123";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.login).setOnClickListener(this);
    }


    @Override


    public void onClick(View arg0) {
        Button b = (Button)findViewById(R.id.login);
        EditText uName = (EditText) findViewById(R.id.userLogin);
        EditText uPass = (EditText) findViewById(R.id.userPassword);
        //b.setClickable(false);
//        String userName= String.valueOf(uName.getText());
//        String userPassword= String.valueOf(uPass.getText());

        String userName= "yslsourav5";
        String userPassword= "test123";

        LoginApp loginApp = new LoginApp();
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Logging in...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        try {
            loginApp.execute(coBrandUserName, coBrandPassword, userName, userPassword).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //LoginApp.doLogin(coBrandUserName, coBrandPassword, userName, userPassword);
        dialog.dismiss();
        Intent i=new Intent(MainActivity.this, Homescreen.class);
        startActivity(i);

    }
}