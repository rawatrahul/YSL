package com.rrawat.ysl_hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by RRawat on 12-06-2015.
 */
public class TransactionAdapter extends BaseAdapter {
    Context context;
    String[] data;
    private static LayoutInflater inflater = null;

    public TransactionAdapter(Context context, String[] data) {
        // TODO Auto-generated constructor stub
        this.context = context;

        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String params[] = data[position].split(" - ");
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.transaction_list_row, null);
        TextView textView = (TextView) vi.findViewById(R.id.date);
        textView.setText(params[0]);
        textView = (TextView) vi.findViewById(R.id.desc);
        textView.setText(params[1]);
        textView = (TextView) vi.findViewById(R.id.amount);
        textView.setText("$ "+params[2]);
        textView = (TextView) vi.findViewById(R.id.type);
        textView.setText(params[3]);
        return vi;
    }
}
