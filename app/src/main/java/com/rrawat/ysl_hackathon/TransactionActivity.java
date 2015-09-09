package com.rrawat.ysl_hackathon;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.concurrent.ExecutionException;


public class TransactionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

//        String restUrl = intent.getStringExtra(TextToAPIParameter.RET_PARAM_REST_URL);
//        int magnitude = Integer.parseInt(intent.getStringExtra(TextToAPIParameter.RET_PARAM_MAGNITUDE));



        TransactionApp transactionApp = new TransactionApp();
        try {
            transactionApp.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("Transaction", transactionApp.allTransactions.toString());
        String[] accountData = transactionApp.allTransactions.datatoString().split("\n");

        ListView listView = (ListView) findViewById(R.id.transaction_list);

        listView.setAdapter(new TransactionAdapter(this,accountData));

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
