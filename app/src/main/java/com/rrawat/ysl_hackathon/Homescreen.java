package com.rrawat.ysl_hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by RRawat on 11-06-2015.
 */
public class Homescreen extends Activity implements View.OnClickListener {

    private static final String TAG = "Speech";
    private String mActivityTitle;
    private Accounts uAccounts;
    private TextView searchText;
    private SpeechRecognizer sr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        ImageButton speakButton = (ImageButton) findViewById(R.id.search_button);
        searchText = (TextView) findViewById(R.id.speechsearch);
        speakButton.setOnClickListener(this);
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new listener());

        AccountApp accountApp = new AccountApp();
        accountApp.execute();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("rahul", accountApp.allAccounts.toString());
        String accountData[] = (accountApp.allAccounts.datatoString()).split("\n");

        ListView listView = (ListView) findViewById(R.id.home);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, accountData);
        listView.setAdapter(new AccountAdapter(this,accountData));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    class listener implements RecognitionListener
    {
        public void onReadyForSpeech(Bundle params)
        {
            Log.d(TAG, "onReadyForSpeech");
        }
        public void onBeginningOfSpeech()
        {
            Log.d(TAG, "onBeginningOfSpeech");
        }
        public void onRmsChanged(float rmsdB)
        {
            Log.d(TAG, "onRmsChanged");
        }
        public void onBufferReceived(byte[] buffer)
        {
            Log.d(TAG, "onBufferReceived");
        }
        public void onEndOfSpeech()
        {
            Log.d(TAG, "onEndofSpeech");
        }
        public void onError(int error)
        {
            Log.d(TAG,  "error " +  error);
            searchText.setText("error " + error);
        }
        public void onResults(Bundle results)
        {
            String str = new String();
            Log.d(TAG, "onResults " + results);
            ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            for (int i = 0; i < data.size(); i++)
            {
                Log.d(TAG, "result " + data.get(i));
                str += data.get(i);
            }
            //for first result
            searchText.setText(String.valueOf(data.get(0)));
            TextToAPIParameter textToAPIParameter = new TextToAPIParameter();
            Log.d("Karthik", String.valueOf(data.get(0)));
            HashMap<String, String> paramMap = textToAPIParameter.getAPIParametersFromSpokenText(String.valueOf(data.get(0)));
            Log.d("Karthik",paramMap.toString());
            String restURL = paramMap.get(TextToAPIParameter.RET_PARAM_REST_URL);
            String methodName = paramMap.get(TextToAPIParameter.RET_PARAM_METHOD_NAME);
            Intent i;

            switch (methodName) {

                case TextToAPIParameter.ACCOUNT_ID :
                    i = new Intent(Homescreen.this,AccountActivity.class);
                    i.putExtra(TextToAPIParameter.RET_PARAM_REST_URL,paramMap.get(TextToAPIParameter.RET_PARAM_REST_URL));
                    i.putExtra(TextToAPIParameter.RET_PARAM_MAGNITUDE,paramMap.get(TextToAPIParameter.RET_PARAM_MAGNITUDE));
                    startActivity(i);
                    break;

                case TextToAPIParameter.HOLDING_ID :
                    break;

                case TextToAPIParameter.TRANSACTION_ID :
                    i = new Intent(Homescreen.this,TransactionActivity.class);
                    i.putExtra(TextToAPIParameter.RET_PARAM_REST_URL,paramMap.get(TextToAPIParameter.RET_PARAM_REST_URL));
                    i.putExtra(TextToAPIParameter.RET_PARAM_MAGNITUDE,paramMap.get(TextToAPIParameter.RET_PARAM_MAGNITUDE));
                    startActivity(i);
                    break;
                default:
                    break;

            }
        }
        public void onPartialResults(Bundle partialResults)
        {
            Log.d(TAG, "onPartialResults");
        }
        public void onEvent(int eventType, Bundle params)
        {
            Log.d(TAG, "onEvent " + eventType);
        }
    }
    public void onClick(View v) {
        if (v.getId() == R.id.search_button)
        {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"com.rrawat.ysl_hackathon");

            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5);
            sr.startListening(intent);
            Log.i("111111","11111111");
        }
    }

}
