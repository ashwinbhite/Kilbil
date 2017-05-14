package com.example.ashtech.kilbil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

public class DiaryActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<DiaryListItem>> listDataChild;
    private List<DiaryListItem> school_info;
    private List<DiaryListItem> prarthna;

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

        if(expListView!=null){
            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    System.out.println(groupPosition);
                    Bundle b= new Bundle();
                    int resid=listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getResId();
                    System.out.println(resid);
                    b.putInt("resId",resid);

                    ExtendedFragment extentedfragment =new ExtendedFragment();
                    extentedfragment.setArguments(b);
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.diary_container,extentedfragment,"ExtendedFragment").addToBackStack("ExtendedFragment").commit();
                    System.out.println("frag open");

                    return true;
                }
            });
        }
    }


    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<DiaryListItem>>();

        // Adding child data
        listDataHeader.add("शाळेची माहिती व नियम");
        listDataHeader.add("प्रार्थना");


        // Adding child data
         school_info = new ArrayList<DiaryListItem>();
        school_info.add(new DiaryListItem(R.string.schooltime,"School Timing"));
        school_info.add(new DiaryListItem(R.string.meetingtime,"Timing for Meeting"));
        school_info.add(new DiaryListItem(R.string.Pratidnya,"List of Holidays"));
        school_info.add(new DiaryListItem(R.string.Pratidnya,"Diwali Vacation"));
        school_info.add(new DiaryListItem(R.string.Pratidnya,"Winter Vacation"));
        school_info.add(new DiaryListItem(R.string.Pratidnya,"Summer Vacation"));
        school_info.add(new DiaryListItem(R.string.Pratidnya,"Conduct for students"));
        school_info.add(new DiaryListItem(R.string.Pratidnya,"Withdrawl Rules"));

         prarthna = new ArrayList<DiaryListItem>();
        prarthna.add(new DiaryListItem(R.string.Pratidnya,"शक्ती दे आम्हास"));
        prarthna.add(new DiaryListItem(R.string.janganman,"जन गण मन"));
        prarthna.add(new DiaryListItem(R.string.Pratidnya,"प्रतिज्ञा"));
        prarthna.add(new DiaryListItem(R.string.sarasati,"सरस्वती स्तोत्र"));
        prarthna.add(new DiaryListItem(R.string.hansvahini,"हे हंस वाहिनी"));
        prarthna.add(new DiaryListItem(R.string.astoma,"ॐ असतोमा"));
        prarthna.add(new DiaryListItem(R.string.shlok,"श्लोक"));
        prarthna.add(new DiaryListItem(R.string.ekatmtamantra,"एकात्मता मंत्र"));
        prarthna.add(new DiaryListItem(R.string.namskarmaza,"नमस्कार माझा या ज्ञानमंदिरा"));
        prarthna.add(new DiaryListItem(R.string.shantimantra,"शांति मंत्र"));
        prarthna.add(new DiaryListItem(R.string.pasaydan,"पसायदान"));




        listDataChild.put(listDataHeader.get(0), school_info); // Header, Child data
        listDataChild.put(listDataHeader.get(1), prarthna);

    }




}