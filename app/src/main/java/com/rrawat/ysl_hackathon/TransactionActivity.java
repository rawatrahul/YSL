package com.rrawat.ysl_hackathon;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.Arrays;


public class TransactionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Intent intent = getIntent();
        String restUrl = intent.getStringExtra(TextToAPIParameter.RET_PARAM_REST_URL);
        int magnitude = Integer.parseInt(intent.getStringExtra(TextToAPIParameter.RET_PARAM_MAGNITUDE));



        TransactionApp transactionApp = new TransactionApp();
        transactionApp.execute(restUrl);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Transaction", transactionApp.allTransactions.datatoString());
        String[] accountData = transactionApp.allTransactions.datatoString().split("\n");
        int maxRange = magnitude;
        if(magnitude > accountData.length) {
            maxRange = accountData.length ;
            /**
             * TODO : Text to speech saying requested number transaction can't be displayed !!!
             */
        } else if(magnitude <= 0) {
            maxRange = accountData.length;
        }
        String[] transData = Arrays.copyOfRange(accountData, 0, maxRange);

        ListView listView = (ListView) findViewById(R.id.transaction_list);

        listView.setAdapter(new TransactionAdapter(this,transData));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction, menu);
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
