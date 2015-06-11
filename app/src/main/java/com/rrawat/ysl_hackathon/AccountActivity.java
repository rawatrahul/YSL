package com.rrawat.ysl_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.Locale;


public class AccountActivity extends ActionBarActivity {

    TextToSpeech t1;
    private static final CharSequence exceptionMessage = "Sorry there are no accounts for your request !!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        String restUrl = intent.getStringExtra(TextToAPIParameter.RET_PARAM_REST_URL);
        int magnitude = Integer.parseInt(intent.getStringExtra(TextToAPIParameter.RET_PARAM_MAGNITUDE));

        AccountApp accountApp = new AccountApp();
        accountApp.execute(restUrl);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String accountData[] = null;

        if(accountApp != null && accountApp.allAccounts != null && accountApp.allAccounts.getAccounts() != null) {
            Log.d("rahul", accountApp.allAccounts.toString());
            accountData = (accountApp.allAccounts.datatoString()).split("\n");
            ListView listView = (ListView) findViewById(R.id.home);

            listView.setAdapter(new AccountAdapter(this, accountData));
        } else {

            t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.US);                    }
                }
            });
            t1.speak(exceptionMessage, TextToSpeech.QUEUE_FLUSH, null,null);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account, menu);
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
