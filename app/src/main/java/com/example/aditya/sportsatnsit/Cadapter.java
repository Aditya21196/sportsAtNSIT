package com.example.aditya.sportsatnsit;

import android.content.Context;
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

public class Cadapter extends ArrayAdapter<Entry> {

    public Cadapter(Context context, List<Entry> data) {
        super(context, R.layout.c_custom_row, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater lf = LayoutInflater.from(getContext());
        View customView = lf.inflate(R.layout.c_custom_row, parent, false);
        Entry rData = getItem(position);
        TextView tv_t1 = (TextView) customView.findViewById(R.id.t1f2);
        TextView tv_t2 = (TextView) customView.findViewById(R.id.t2f2);
        TextView tv_date = (TextView) customView.findViewById(R.id.datef2);
        TextView tv_score1 = (TextView) customView.findViewById(R.id.Score1);
        TextView tv_score2 = (TextView) customView.findViewById(R.id.Score2);
        tv_t1.setText(rData.team1);
        tv_t2.setText(rData.team2);
        tv_date.setText(rData.date);
        tv_score1.setText(rData.score1);
        tv_score2.setText(rData.score2);
        return customView;
    }
}
