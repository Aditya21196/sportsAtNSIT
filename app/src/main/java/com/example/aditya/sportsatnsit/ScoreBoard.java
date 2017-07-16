package com.example.aditya.sportsatnsit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScoreBoard extends AppCompatActivity {

    private ArrayList<Entry> entriesPending;
    private ArrayList<Entry> entriesCompleted;
    private DatabaseReference mDatabase;
    private ListView listView;
    private ListView listView2;
    static boolean calledAlready = false;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar = (ProgressBar) LayoutInflater.from(this).inflate(R.layout.progress_bar, null);

//        progressBar = (ProgressBar)findViewById(R.id.progressBar1);
//        progressBar.setVisibility(View.VISIBLE);

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_score_board);

        if (FirebaseActivity.home) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("db2").child(MainActivity.YEAR).child(FirebaseActivity.selectedSport);
            home();
        } else {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("db2").child(FirebaseActivity.selectedYear).child(FirebaseActivity.selectedSport);
            generic();
        }
    }


    void home() {
        final String branchSection = MainActivity.BRANCH + "-" + MainActivity.SECTION;

        entriesPending = new ArrayList<>();
        entriesCompleted = new ArrayList<>();

        Query mySortingQuery = mDatabase.orderByChild("timeInMiliSec");

        listView = (ListView) findViewById(R.id.lv);
        listView2 = (ListView) findViewById(R.id.lv2);

        final Padapter myAdapter = new Padapter(this, entriesPending);
        final Cadapter myAdapter2 = new Cadapter(this, entriesCompleted);

        listView.setAdapter(myAdapter);
        listView2.setAdapter(myAdapter2);
        listView2.addFooterView(progressBar);
        mySortingQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                listView2.removeFooterView(progressBar);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mySortingQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Entry value = dataSnapshot.getValue(Entry.class);
                if (value.team1.equals(branchSection) || value.team1.equals(branchSection)) {
                    if (value.score1.equals("-1")) {
                        entriesPending.add(value);
                        myAdapter.notifyDataSetChanged();
                        ListUtils.setDynamicHeight(listView);
                    } else {
                        entriesCompleted.add(value);
                        myAdapter2.notifyDataSetChanged();
                        ListUtils.setDynamicHeight(listView2);
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
        entriesPending = new ArrayList<>();
        entriesCompleted = new ArrayList<>();

        Query mySortingQuery = mDatabase.orderByChild("timeInMiliSec");

        listView = (ListView) findViewById(R.id.lv);
        listView2 = (ListView) findViewById(R.id.lv2);

        final Padapter myAdapter = new Padapter(this, entriesPending);
        final Cadapter myAdapter2 = new Cadapter(this, entriesCompleted);

        listView.setAdapter(myAdapter);
        listView2.setAdapter(myAdapter2);
        listView2.addFooterView(progressBar);

        mySortingQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                listView2.removeFooterView(progressBar);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

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
                    ListUtils.setDynamicHeight(listView);
                } else {
                    entriesCompleted.add(value);
                    myAdapter2.notifyDataSetChanged();
                    ListUtils.setDynamicHeight(listView2);
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

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = MeasureSpec.makeMeasureSpec(mListView.getWidth(), MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }

}


