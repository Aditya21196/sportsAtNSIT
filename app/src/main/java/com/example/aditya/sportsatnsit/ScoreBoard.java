package com.example.aditya.sportsatnsit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ScoreBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        TextView text = (TextView) findViewById(R.id.lol);
        if (FirebaseActivity.home)
            text.setText(MainActivity.YEAR + " " + MainActivity.BRANCH + " " + MainActivity.SECTION + " " + FirebaseActivity.selectedSport);
        else
            text.setText(FirebaseActivity.selectedYear + " " + FirebaseActivity.selectedSport);
    }
}
