package com.example.ashtech.kilbil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class DiaryActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("School Info.");
        listDataHeader.add("प्रार्थना");


        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("School Timing");
        top250.add("Timing for Meeting");
        top250.add("List of Holidays");
        top250.add("Diwali Vacation");
        top250.add("Winter Vacation");
        top250.add("Summer Vacation");
        top250.add("Conduct for students");
        top250.add("Withdrawl Rules");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("शक्ती दे आम्हास");
        nowShowing.add("जन गण मन");
        nowShowing.add("प्रतिज्ञा");
        nowShowing.add("सरस्वती स्तोत्र");
        nowShowing.add("हे हंस वाहिनी");
        nowShowing.add("ॐ असतोमा");
        nowShowing.add("श्लोक");
        nowShowing.add("एकात्मता मंत्र");
        nowShowing.add("नमस्कार माझा या ज्ञानमंदिरा");
        nowShowing.add("शांति मंत्र");
        nowShowing.add("पसायदान");




        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);

    }
}