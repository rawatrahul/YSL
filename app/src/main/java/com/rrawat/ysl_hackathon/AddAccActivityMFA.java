package com.rrawat.ysl_hackathon;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class AddAccActivityMFA extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acc_activity_mf);
        final EditText providerText = (EditText) findViewById(R.id.providerIdMFA);
        final EditText loginText = (EditText) findViewById(R.id.site_login_mfa);
        final EditText passText = (EditText) findViewById(R.id.site_pass_mfa);
        final Button addButtonMFA = (Button) findViewById(R.id.addSiteMFA);
        addButtonMFA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String providerId = providerText.getText().toString();
                    String loginSite = loginText.getText().toString();
                    String passSite = passText.getText().toString();

                    AddMfaAccApp addMfaAccApp = new AddMfaAccApp();

                    addMfaAccApp.execute(providerId, loginSite, passSite).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_acc_activity_m, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
