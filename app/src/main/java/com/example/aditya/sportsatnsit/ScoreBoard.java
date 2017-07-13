package com.example.aditya.sportsatnsit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.Arrays;

public class ScoreBoard extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        text = (TextView) findViewById(R.id.lol);
        if (FirebaseActivity.home)
            home();
        else
            generic();
    }

    void home() {
        text.setText(MainActivity.YEAR + " " + MainActivity.BRANCH + " " + MainActivity.SECTION + " " + FirebaseActivity.selectedSport);
        Pdata d1 = new Pdata("COE 1", "COE 2", "1st august", "1:30");
        Pdata[] dat = {
                d1, d1, d1, d1, d1, d1
        };
        ExpandableHeightListView listView = (ExpandableHeightListView) findViewById(R.id.lv);
        Padapter myAdapter = new Padapter(this, Arrays.asList(dat));
        listView.setExpanded(true);

        listView.setAdapter(myAdapter);
        Cdata df1 = new Cdata("ICE 1", "ICE 2", "2nd august", "2:0");
        Cdata[] dataf2 = {
                df1, df1, df1, df1, df1, df1
        };
        ExpandableHeightListView listView2 = (ExpandableHeightListView) findViewById(R.id.lv2);
        Cadapter myAdapter2 = new Cadapter(this, Arrays.asList(dataf2));
        listView2.setExpanded(true);
        listView2.setAdapter(myAdapter2);
    }

    void generic() {
        text.setText(FirebaseActivity.selectedYear + " " + FirebaseActivity.selectedSport);
        text.setText(MainActivity.YEAR + " " + MainActivity.BRANCH + " " + MainActivity.SECTION + " " + FirebaseActivity.selectedSport);
        Pdata d1 = new Pdata("COE 1", "COE 2", "1st august", "1:30");
        Pdata[] dat = {
                d1, d1, d1, d1, d1, d1
        };
        ExpandableHeightListView listView = (ExpandableHeightListView) findViewById(R.id.lv);
        Padapter myAdapter = new Padapter(this, Arrays.asList(dat));
        listView.setExpanded(true);

        listView.setAdapter(myAdapter);
        Cdata df1 = new Cdata("ICE 1", "ICE 2", "2nd august", "2:0");
        Cdata[] dataf2 = {
                df1, df1, df1, df1, df1, df1
        };
        ExpandableHeightListView listView2 = (ExpandableHeightListView) findViewById(R.id.lv2);
        Cadapter myAdapter2 = new Cadapter(this, Arrays.asList(dataf2));
        listView2.setExpanded(true);
        listView2.setAdapter(myAdapter2);

    }
}
