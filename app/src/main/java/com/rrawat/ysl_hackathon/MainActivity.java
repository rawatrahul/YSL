package com.rrawat.ysl_hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

    private static String coBrandUserName = "yslsandbox20";
    private static String coBrandPassword = "Yodlee@123";


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
        String userName= String.valueOf(uName.getText());
        String userPassword= String.valueOf(uPass.getText());

            LoginApp loginApp = new LoginApp();
            loginApp.execute(coBrandUserName, coBrandPassword, userName, userPassword);
            //LoginApp.doLogin(coBrandUserName, coBrandPassword, userName, userPassword);

        Intent i=new Intent(MainActivity.this, Homescreen.class);
        startActivity(i);

    }
}