package com.example.aditya.sportsatnsit;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aditya on 13/7/17.
 */

public class Padapter extends ArrayAdapter<Pdata> {

    public Padapter(Context context, List<Pdata> data) {
        super(context, R.layout.p_custom_row, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater lf = LayoutInflater.from(getContext());
        View customView = lf.inflate(R.layout.p_custom_row, parent, false);
        Pdata rData = getItem(position);
        TextView tv_t1 = (TextView) customView.findViewById(R.id.t1);
        TextView tv_t2 = (TextView) customView.findViewById(R.id.t2);
        TextView tv_date = (TextView) customView.findViewById(R.id.date);
        TextView tv_time = (TextView) customView.findViewById(R.id.time);
        tv_t1.setText(rData.team1);
        tv_t2.setText(rData.team2);
        tv_date.setText(rData.date);
        tv_time.setText(rData.time);
        return customView;
    }
}
