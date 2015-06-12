package com.rrawat.ysl_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class SmartActivity extends ActionBarActivity {

    TextToSpeech t1;
    private static final CharSequence exceptionMessage = "Sorry there are no accounts for your request !!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        String smartKeyword = intent.getStringExtra(TextToAPIParameter.SMART_KEYWORD);
        String restUrl = intent.getStringExtra(TextToAPIParameter.RET_PARAM_REST_URL);

        getSmartDetails(smartKeyword, restUrl);
    }

    private void getSmartDetails(String smartKeyword, String restUrl) {
        switch(smartKeyword) {
            case TextToAPIParameter.MAX_EXPENSE_CAT :
                String maxexpensedetails = getMaxExpenseCategory(restUrl);
                String category = maxexpensedetails.split(":")[0];
                String balance = maxexpensedetails.split(":")[1];
                setContentView(R.layout.smart_activity_ec);
                TextView categoryText = (TextView) findViewById(R.id.category);
                TextView balanceText = (TextView) findViewById(R.id.amt);
                categoryText.setText(category);
                balanceText.setText("$ "+ balance);
                break;

            case TextToAPIParameter.NET_WORTH :
                String networth = getNetworth(restUrl).toString();
                setContentView(R.layout.smart_activity_nw);
                TextView netWorth = (TextView) findViewById(R.id.networth);
                netWorth.setText("$ "+networth);
                break;

            default:
                break;
        }
    }

    private static final List<String> negativeTags = new ArrayList<>();
    static {
        negativeTags.add("loan");
        negativeTags.add("mortgage");
        negativeTags.add("creditCard");
        negativeTags.add("bill");
    }



    private Double getNetworth(String restUrl) {
        AccountApp accountApp = new AccountApp();
        accountApp.execute(restUrl);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String accountData[] = null;

        double totalNetworth = 0;

        if(accountApp != null && accountApp.allAccounts != null && accountApp.allAccounts.getAccounts() != null) {
            Log.d("rahul", accountApp.allAccounts.toString());
            Account[] accounts = accountApp.allAccounts.getAccounts();

            for(Account account : accounts) {
                if(negativeTags.contains(account.getCONTAINER())) {
                    totalNetworth -= account.getBalance();
                } else {
                    totalNetworth += account.getBalance();
                }
            }

        }
        return totalNetworth;
    }

    private String getMaxExpenseCategory(String restUrl) {
        HashMap<String, Double> categoryBalanceMap = new HashMap<>();
        String maxCatgoryDetails = null;

        TransactionApp transactionApp = new TransactionApp();
        transactionApp.execute(restUrl);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Transaction[] transactions = transactionApp.allTransactions.getTransaction();

        if(transactions != null) {
            for (Transaction transaction : transactions) {
                String category = transaction.getCategory();
                Double balanceForCat = categoryBalanceMap.get(category);
                if(balanceForCat == null) {
                    balanceForCat = 0.0;
                }
                if(transaction.getBaseType().equalsIgnoreCase("credit")) {
                    categoryBalanceMap.put(category, (balanceForCat - transaction.getAmount()));
                } else {
                    categoryBalanceMap.put(category, (balanceForCat + transaction.getAmount()));
                }
            }

            maxCatgoryDetails = getMaxCategory(categoryBalanceMap);
        }

        return maxCatgoryDetails;
    }

    private String getMaxCategory(HashMap<String, Double> categoryBalanceMap) {

        Double max = 0.0;
        String expenseCategory = null;
        Set<String> categorySet = categoryBalanceMap.keySet();
        for (String category : categorySet) {

            if(categoryBalanceMap.get(category) > max) {
                max = categoryBalanceMap.get(category);
                expenseCategory = category;
            }
        }
        return expenseCategory+":"+max;
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
