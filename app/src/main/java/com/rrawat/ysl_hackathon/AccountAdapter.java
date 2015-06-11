package com.rrawat.ysl_hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by RRawat on 11-06-2015.
 */
public class AccountAdapter extends BaseAdapter{
    Context context;
    String[] data;
    private static LayoutInflater inflater = null;

    public AccountAdapter(Context context, String[] data) {
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
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.listview_row, null);
        TextView text = (TextView) vi.findViewById(R.id.accname);
        text.setText(data[0]);
        text = (TextView) vi.findViewById(R.id.accno);
        text.setText(data[1]);
        text = (TextView) vi.findViewById(R.id.accbal);
        text.setText(data[2]);

        return vi;
    }
}
