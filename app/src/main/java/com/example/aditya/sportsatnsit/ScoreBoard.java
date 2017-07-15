package com.example.aditya.sportsatnsit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

class Entry {
    String date;
    String time;
    Long timeInMiliSec;
    String team1;
    String team2;
    String score1;
    String score2;

    Entry() {

    }

    Entry(String date, String time, Long timeInMiliSec, String team1, String team2, String score1, String score2) {
        this.date = date;
        this.time = time;
        this.timeInMiliSec = timeInMiliSec;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
    }
}

public class ScoreBoard extends AppCompatActivity {

    private TextView text;
    private ArrayList<Entry> entriesPending;
    private ArrayList<Entry> entriesCompleted;
    private DatabaseReference mDatabase;
    private ExpandableHeightListView listView;
    private ExpandableHeightListView listView2;
    static boolean calledAlready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_score_board);

        text = (TextView) findViewById(R.id.lol);
        if (FirebaseActivity.home) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("db2").child(MainActivity.YEAR).child(FirebaseActivity.selectedSport);
            home();
        } else {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("db2").child(FirebaseActivity.selectedYear).child(FirebaseActivity.selectedSport);
            generic();
        }
    }


    void home() {
        text.setText(MainActivity.YEAR + " " + MainActivity.BRANCH + " " + MainActivity.SECTION + " " + FirebaseActivity.selectedSport);
        final String branchSection = MainActivity.BRANCH + "-" + MainActivity.SECTION;

        entriesPending = new ArrayList<>();
        entriesCompleted = new ArrayList<>();

        Query mySortingQuery = mDatabase.orderByChild("timeInMiliSec");

        listView = (ExpandableHeightListView) findViewById(R.id.lv);
        listView2 = (ExpandableHeightListView) findViewById(R.id.lv2);

        final Padapter myAdapter = new Padapter(this, entriesPending);
        final Cadapter myAdapter2 = new Cadapter(this, entriesCompleted);

        listView.setAdapter(myAdapter);
        listView2.setAdapter(myAdapter2);

        mySortingQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Entry value = dataSnapshot.getValue(Entry.class);
                if (value.team1.equals(branchSection) || value.team1.equals(branchSection)) {
                    if (value.score1.equals("-1")) {
                        entriesPending.add(value);
                        myAdapter.notifyDataSetChanged();
                    } else {
                        entriesCompleted.add(value);
                        myAdapter2.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    void generic() {
        text.setText(FirebaseActivity.selectedYear + " " + FirebaseActivity.selectedSport);

        entriesPending = new ArrayList<>();
        entriesCompleted = new ArrayList<>();

        Query mySortingQuery = mDatabase.orderByChild("timeInMiliSec");

        listView = (ExpandableHeightListView) findViewById(R.id.lv);
        listView2 = (ExpandableHeightListView) findViewById(R.id.lv2);

        final Padapter myAdapter = new Padapter(this, entriesPending);
        final Cadapter myAdapter2 = new Cadapter(this, entriesCompleted);

        listView.setExpanded(true);
        listView2.setExpanded(true);
        listView.setAdapter(myAdapter);
        listView2.setAdapter(myAdapter2);

//        mySortingQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> entries = dataSnapshot.getChildren();
//                for (DataSnapshot entry : entries) {
//                    Entry value = entry.getValue(Entry.class);
//                    if (value.score1.equals("-1"))
//                        entriesPending.add(value);
//                    else
//                        entriesCompleted.add(value);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        final Padapter myAdapter = new Padapter(this, entriesPending);
//        final Cadapter myAdapter2 = new Cadapter(this, entriesCompleted);
//        listView.setExpanded(true);
//        listView2.setExpanded(true);
//        listView.setAdapter(myAdapter);
//        listView2.setAdapter(myAdapter2);

        mySortingQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Entry value = dataSnapshot.getValue(Entry.class);
                if (value.score1.equals("-1")) {
                    entriesPending.add(value);
                    myAdapter.notifyDataSetChanged();
                } else {
                    entriesCompleted.add(value);
                    myAdapter2.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
