package com.rrawat.ysl_hackathon;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class AddAccActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acc);

        final EditText providerText = (EditText) findViewById(R.id.providerId);
        final EditText loginText = (EditText) findViewById(R.id.site_login);
        final EditText passText = (EditText) findViewById(R.id.site_pass);
        final Button addButton = (Button) findViewById(R.id.addSite);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAccApp addAccApp = new AddAccApp();
                try {
                    String providerId = providerText.getText().toString();
                    String loginSite = loginText.getText().toString();
                    String passSite = passText.getText().toString();

                    addAccApp.execute(providerId, loginSite, passSite).get();
                    NotificationCompat.Builder notif = new NotificationCompat.Builder(getBaseContext()).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Refresh Status").setContentText(AddAccApp.refreshStatus.getRefreshInfo().getRefreshStatus());
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(1, notif.build());
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
        getMenuInflater().inflate(R.menu.menu_add_acc, menu);
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
