package com.example.aditya.sportsatnsit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FirebaseActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FrameLayout mFrame;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    home();
                    return true;
                case R.id.navigation_all:
                    all();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        mFrame = (FrameLayout) findViewById(R.id.frame);
//        mTextMessage = (TextView) findViewById(R.id.message);
        home();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void home() {
//        mTextMessage.setText(MainActivity.YEAR + " " + MainActivity.BRANCH + " " + MainActivity.SECTION);
        mFrame.removeAllViews();

        ListView list = new ListView(this);
        ArrayList<String> sportsArrayList = new ArrayList<>();
        sportsArrayList.add("Football");
        sportsArrayList.add("Kabaddi");
        sportsArrayList.add("Cricket");
        sportsArrayList.add("Basketball");
        sportsArrayList.add("Tabletennis");
        sportsArrayList.add("Chess");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                sportsArrayList);
        list.setAdapter(arrayAdapter);
        mFrame.addView(list);
    }

    private void all() {
        mFrame.removeAllViews();

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 50, 0, 0);
        ll.setLayoutParams(params);
        mFrame.addView(ll);

        Spinner spinnerYear = new Spinner(this);
        params.setMargins(20, 0, 0, 0);
        spinnerYear.setLayoutParams(params);
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("1st Year");
        spinnerArray.add("2nd Year");
        spinnerArray.add("3rd Year");
        spinnerArray.add("4th Year");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapter);
        ((LinearLayout) ll).addView(spinnerYear);

        ListView list = new ListView(this);
        params.setMargins(0, 30, 0, 0);
        list.setLayoutParams(params);
        ArrayList<String> sportsArrayList = new ArrayList<>();
        sportsArrayList.add("Football");
        sportsArrayList.add("Kabaddi");
        sportsArrayList.add("Cricket");
        sportsArrayList.add("Basketball");
        sportsArrayList.add("Tabletennis");
        sportsArrayList.add("Chess");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                sportsArrayList);
        list.setAdapter(arrayAdapter);
        ll.addView(list);
    }
}
