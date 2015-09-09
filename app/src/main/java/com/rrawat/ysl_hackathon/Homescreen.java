package com.rrawat.ysl_hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by RRawat on 11-06-2015.
 */
public class Homescreen extends Activity {

    private static final String TAG = "Speech";
    private String mActivityTitle;
    private Accounts uAccounts;
    private TextView searchText;
    private SpeechRecognizer sr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        final ListView listView = (ListView) findViewById(R.id.home);
        final String[] values = {"Site Search",
                "Add Site Account(Non MFA)",
                "Add Site Account(MFA)",
                "Accounts",
                "Bills",
                "Holdings",
                "Transactions",
                "Logout" };
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.menu_item,R.id.item_name,values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Intent intent;
                switch(position){
                    case 0: intent = new Intent(getApplicationContext(),SiteActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(),values[position],Toast.LENGTH_SHORT).show();
                        break;
                    case 1: intent = new Intent(getApplicationContext(),AddAccActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(),values[position],Toast.LENGTH_SHORT).show();
                        break;
                    case 2: intent = new Intent(getApplicationContext(),AddAccActivityMFA.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(),values[position],Toast.LENGTH_SHORT).show();
                        break;
                    case 3: intent = new Intent(getApplicationContext(),AccountActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(),values[position],Toast.LENGTH_SHORT).show();
                        break;
                    case 4: intent = new Intent(getApplicationContext(),BillsActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(),values[position],Toast.LENGTH_SHORT).show();
                        break;
                    case 5: intent = new Intent(getApplicationContext(),HoldingActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(),values[position],Toast.LENGTH_SHORT).show();
                        break;
                    case 6: intent = new Intent(getApplicationContext(),TransactionActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(),values[position],Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getBaseContext(),values[position],Toast.LENGTH_SHORT).show();


                }
                //Toast.makeText(getBaseContext(),values[position],Toast.LENGTH_SHORT).show();
            }
        });


    }

}
